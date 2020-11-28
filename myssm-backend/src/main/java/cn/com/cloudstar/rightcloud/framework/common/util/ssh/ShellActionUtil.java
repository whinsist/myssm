/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.ssh;

import com.alibaba.fastjson.JSON;

import cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean.AllocateIp4LparParam;
import cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean.BaseShellParam;
import cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean.CmdParamsFiller;
import cn.com.cloudstar.rightcloud.framework.common.util.ssh.res.JschResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hong.Wu
 * @date: 11:07 2018/10/15
 */
public class ShellActionUtil {
    private static Logger logger = LoggerFactory.getLogger(ShellActionUtil.class);

    public static JschResult execCmdWithPreDeal(String cmd, BaseShellParam param){
        return execCmd(CmdParamsFiller.fill(cmd, param), param);
    }

    public static JschResult execCmd(String cmd, BaseShellParam param){
        System.out.println("");
        JschUtils utils = new JschUtils(param.getRemoteUser(), param.getHostIp(), param.getHostPort())
                .withPassword(param.getRemotePwd());
        try {
            logger.info("cmd -----  : {}", cmd);
            JschResult result = utils.execWithLineFormatAndStatus(cmd).getResultsAndStatus();
            logger.info("cmd result : {},{}", JSON.toJSONString(result), (result.getExitStatus() != 0 ? " ----- 命令返回状态不为0" : ""));
            return result;
        } finally {
            utils.finish();
        }
    }

    public static void main(String[] args) {
        AllocateIp4LparParam param = new AllocateIp4LparParam();
        param.setHostIp("192.168.93.132");
        param.setHostPort(22);
        param.setRemoteUser("root");
        param.setRemotePwd("123456");
        param.setIp("192.168.93.1");
        JschResult result = ShellActionUtil.execCmdWithPreDeal("grep  \"${ip}\" /etc/hosts ", param);
        System.out.println(result);


        result = ShellActionUtil.execCmdWithPreDeal("grep  \"${ip}\" /etc/hosts ", param);
        System.out.println(result);
    }   

}
