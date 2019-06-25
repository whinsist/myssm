package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.util.ssh.SshKeys;

/**
 * @author Hong.Wu
 * @date: 16:54 2019/06/25
 */
public class Test10Keypair {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        Map<String, String> generate = SshKeys.generate(keyGen);
        String privateKey = generate.get("private");
        String publicKey = generate.get("public");
        String fingerprint = SshKeys.fingerprintPublicKey(generate.get("public"));
        System.out.println("私钥："+privateKey);
        System.out.println("公钥："+publicKey);
        System.out.println("指纹："+fingerprint);
    }   
}
