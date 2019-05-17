/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type JschUtils.
 * <p>
 * Created on 2017/1/17
 *
 * @author Chaohong.Mao
 */
public class JschUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int TIMEOUT = 30 * 1000;
    private String host;
    private String user;
    private int port;
    private List<String> result;
    private Session session;
    private String password;

    /**
     * Instantiates a new Jsch utils.
     *
     * @param user the user
     * @param host the host
     * @param port the port
     */
    public JschUtils(String user, String host, int port) {
        this.host = host;
        this.user = user;
        this.port = port;
    }

    public static void main(String args[]) {
        System.out.println(System.getProperty("java.io.tmpdir"));
//        JschUtils jschUtils = new JschUtils("root", "10.62.132.15", 22).withKey(".ssh/id_rsa");
//        String exec = jschUtils.exec("echo hello").getResult(true);
//        System.out.println(exec);
    }

    public String getPassword() {
        return password;
    }

    /**
     * Is connect boolean.
     *
     * @return the boolean
     */
    public boolean isConnect() {
        return session != null && session.isConnected();
    }

    /**
     * Init with password jsch utils.
     *
     * @param password the password
     * @return the jsch utils
     */
    public JschUtils withPassword(String password) {
        try {
            logger.info("Connect withPassword | host[{}], user[{}], password[{}], port[{}]",
                    this.host,
                    this.user,
                    password,
                    this.port
            );
            // 创建JSch对象
            JSch jsch = new JSch();
            // 根据用户名，主机ip，端口获取一个Session对象
            session = jsch.getSession(this.user, this.host, this.port);

            session.setPassword(password); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties

            session.setTimeout(TIMEOUT); // 设置timeout时间
            session.connect(); // 通过Session建
        } catch (JSchException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        result = new ArrayList<>();
        this.password = password;
        return this;
    }

    /**
     * Init with key jsch utils.
     *
     * @param privateKeyFilePath the private key
     * @return the jsch utils
     */
    public JschUtils withKey(String privateKeyFilePath) {
        logger.info("Connect withKey | host[{}], user[{}], port[{}]", this.host, this.user, this.port);
        try {
            // 创建JSch对象
            JSch jsch = new JSch();
            jsch.addIdentity(privateKeyFilePath);
            // 根据用户名，主机ip，端口获取一个Session对象
            session = jsch.getSession(this.user, this.host, this.port);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            // 为Session对象设置properties
            session.setConfig(config);
            // 设置timeout时间
            session.setTimeout(TIMEOUT);
            // 通过Session建
            session.connect();
        } catch (JSchException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        result = new ArrayList<>();
        return this;
    }

    /**
     * Exec string.
     *
     * @param cmd the cmd
     * @return the string
     */
    public JschUtils execBySudoer(String cmd) {
        Assert.notNull(session, "JSch session is null.");
        Assert.isTrue(session.isConnected(), "JSch session is disconnect.");
        Channel channel = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(cmd);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            ((ChannelExec) channel).setPty(true);

            channel.connect();
            if (!"root".equalsIgnoreCase(user)) {
                OutputStream out = channel.getOutputStream();

                out.write((this.password + "\n").getBytes());
                out.flush();
            }

            reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            String buf;
            while ((buf = reader.readLine()) != null) {
                sb.append(buf);
            }

        } catch (JSchException | IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        result.add(sb.toString());
        return this;
    }

    /**
     * Exec list.
     *
     * @param cmds the cmds
     * @return the list
     */
    public JschUtils exec(String... cmds) {
        for (String cmd : cmds) {
            exec(cmd);
        }
        return this;
    }

    /**
     * Exec string.
     *
     * @param cmd the cmd
     * @return the string
     */
    public JschUtils exec(String cmd) {
        Assert.notNull(session, "JSch session is null.");
        Assert.isTrue(session.isConnected(), "JSch session is disconnect.");
        Channel channel = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(cmd);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            ((ChannelExec) channel).setPty(true);

            channel.connect();
            reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            String buf;
            while ((buf = reader.readLine()) != null) {
                sb.append(buf);
            }

        } catch (JSchException | IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        result.add(sb.toString());
        return this;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public String getResult(boolean disconnect) {
        if (disconnect) {
            finish();
        }
        List<String> result = clearResult();
        return result.size() == 0 ? "" : result.get(0);
    }

    /**
     * Finish.
     */
    public void finish() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    /**
     * Clear result.
     */
    public List<String> clearResult() {
        List<String> res = new ArrayList<>(result);
        result.clear();
        return res;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<String> getResults(boolean disconnect) {
        if (disconnect) {
            finish();
        }
        return clearResult();
    }
}
