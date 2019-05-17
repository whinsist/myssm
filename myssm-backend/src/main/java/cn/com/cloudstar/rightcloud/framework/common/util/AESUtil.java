/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ShiWenQiang
 * @date 2016/6/1
 */
public class AESUtil {

    public static String AES = "AES";

    public static void main(String[] args) {
        String key = "2618c271-27bb-11e6-bc2c-0242ac110002";
        String message = "1750-4a0ac0bedaa61b15b4809b3bc6576c4a-" + key;

        System.out.println("Message:" + message);
        String encryptedMessage = encrypt(message, key);
        System.out.println("encryptedMessage:" + encryptedMessage);
        String decryptMessage = decrypt(encryptedMessage, key);
        System.out.println("decryptMessage:" + decryptMessage);
    }

    public static String encrypt(String data, String key) {
        Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
        byte[] bt = new byte[0];
        try {
            bt = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(bt).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }


    /**
     * 解密文本信息
     */
    public static String decrypt(String message, String key) {
        Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
        byte[] res = new byte[0];
        try {
            res = new BASE64Decoder().decodeBuffer(message);
            res = cipher.doFinal(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(res);
    }

    /**
     * 初始化Cipher
     */
    public static Cipher initAESCipher(String sKey, int cipherMode) {
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(sKey.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(codeFormat, AES);
            cipher = Cipher.getInstance(AES);
            //初始化
            cipher.init(cipherMode, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return cipher;
    }
}
