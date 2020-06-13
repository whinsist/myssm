/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.NonNull;

import cn.com.cloudstar.rightcloud.framework.common.util.PropertiesUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;

/**
 * @author ShiWenQiang
 * @date 2016/6/1
 */
public class AESUtil {
    private static Logger log = LoggerFactory.getLogger(AESUtil.class);

    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    // 默认key,iv 使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！

    private static final String AES = "AES";

    private static final String SHA1PRNG = "SHA1PRNG";

    private static final String PKCS7_PADDING = "AES/CBC/Pkcs7Padding";

    private static final String PKCS5_PADDING = "AES/CBC/Pkcs5Padding";

    private static final String NO_PADDING = "AES/CBC/NOPADDING";

    private static final String SECRET_KEY = "password.secret.key";

    private static final String SECRET_IV = "password.secret.iv";

    private static final String DECRYPT_PREFIX = "DECRYPT--";

    /**
     *  AES方式加密，使用SHA1PRNG
     * @param data
     * @param key
     * @return
     */
    public static String encrypt(String data, String key) {
        Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
        byte[] bt = new byte[0];
        try {
            bt = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bt)
                     .replaceAll("\r\n", "")
                     .replaceAll("\r", "")
                     .replaceAll("\n", "");
    }

    /**
     *  加密
     * @param bytes
     * @param key
     * @return
     */
    public static String encrypt(byte[] bytes, String key) {
        Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
        byte[] bt = new byte[0];
        try {
            bt = cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bt)
                     .replaceAll("\r\n", "")
                     .replaceAll("\r", "")
                     .replaceAll("\n", "");
    }

    /**
     * 解密文本信息
     */
    public static String decrypt(String message, String key) {
        Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
        byte[] res = new byte[0];
        try {
            res = Base64.getDecoder().decode(message);
            res = cipher.doFinal(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(res);
    }

    /**
     * 解密文本信息到二进制
     */
    public static byte[] decryptToBytes(String message, String key) {
        Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
        byte[] res = new byte[0];
        try {
            res = Base64.getDecoder().decode(message);
            res = cipher.doFinal(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 初始化Cipher
     */
    public static Cipher initAESCipher(String sKey, int cipherMode) {
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
            secureRandom.setSeed(sKey.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(codeFormat, AES);
            cipher = Cipher.getInstance(AES);
            //初始化
            cipher.init(cipherMode, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            //To change body of catch statement use File | Settings | File Templates.
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 加密方法
     * <p>补码方式java支持NoPadding,Pkcs5Padding，若要支持Pkcs7Padding，需要新增BouncyCastleProvider支持</p>
     * @param data 要加密的数据
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        if (data == null) {
            return null;
        }
        if (data.length() == 0) {
            return StringUtil.EMPTY;
        }
        try {
            // 算法/模式/补码方式 NoPadding Pkcs5Padding
            Cipher cipher = Cipher.getInstance(PKCS7_PADDING);
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new org.apache.commons.codec.binary.Base64().encodeToString(encrypted)
                                                               .replaceAll("\r\n", "")
                                                               .replaceAll("\r", "")
                                                               .replaceAll("\n", "");
        } catch (Exception e) {
            log.error("加密失败:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 加密方法
     * <p>补码方式java支持NoPadding,Pkcs5Padding，若要支持Pkcs7Padding，需要新增BouncyCastleProvider支持</p>
     * @param data 要加密的数据
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv,boolean hasPrefix) {
        if (data == null) {
            return null;
        }
        if (data.length() == 0) {
            return StringUtil.EMPTY;
        }
        try {
            // 算法/模式/补码方式 NoPadding Pkcs5Padding
            Cipher cipher = Cipher.getInstance(PKCS7_PADDING);
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return DECRYPT_PREFIX + new org.apache.commons.codec.binary.Base64().encodeToString(encrypted)
                                                                                .replaceAll("\r\n", "")
                                                                                .replaceAll("\r", "")
                                                                                .replaceAll("\n", "");
        } catch (Exception e) {
            log.error("加密失败:{}", e.getMessage());
        }
        return null;
    }

    /**
     *  加密
     */
    public static String encrypt(String data) {
        return encrypt(data, PropertiesUtil.getProperty(SECRET_KEY), PropertiesUtil.getProperty(SECRET_IV));
    }

    public static String encrypt(String data,boolean hasPrefix){
        if (StringUtil.isNullOrEmpty(data) || data.startsWith(DECRYPT_PREFIX)) {
            return data;
        }else {
            return encrypt(data, PropertiesUtil.getProperty(SECRET_KEY), PropertiesUtil.getProperty(SECRET_IV),hasPrefix);
        }
    }

    /**
     * 解密方法
     * <p>补码方式java支持NoPadding,Pkcs5Padding，若要支持Pkcs7Padding，需要新增BouncyCastleProvider支持</p>
     * @param data 要解密的数据
     * @return 解密的结果
     */
    public static String decrypt(String data, @NonNull String key, @NonNull String iv) {
        if (StringUtil.isEmpty(data)) {
            return null;
        }
        try {
            byte[] encrypted1 = new org.apache.commons.codec.binary.Base64().decode(data);

            Cipher cipher = Cipher.getInstance(PKCS7_PADDING);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解密失败:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 解密方法
     * <p>补码方式java支持NoPadding,Pkcs5Padding，若要支持Pkcs7Padding，需要新增BouncyCastleProvider支持</p>
     * @param data 要解密的数据
     * @return 解密的结果
     */
    public static String decrypt(String data, @NonNull String key, @NonNull String iv,boolean hasPrefix) {
        if (StringUtil.isEmpty(data)) {
            return null;
        }
        try {
            byte[] encrypted1 = new org.apache.commons.codec.binary.Base64().decode(data);

            Cipher cipher = Cipher.getInstance(PKCS7_PADDING);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String decryptResult = new String(original, StandardCharsets.UTF_8);
            char[] decryptResultChar = decryptResult.toCharArray();
            StringBuffer sb = new StringBuffer();
            for(char c:decryptResultChar){
                if (!String.valueOf("\u0000").equals(String.valueOf(c))){
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("解密失败:{}", e.getMessage());
        }
        return null;
    }

    /**
     *  解密
     */
    public static String decrypt(String data) {
        return decrypt(data, PropertiesUtil.getProperty(SECRET_KEY), PropertiesUtil.getProperty(SECRET_IV));
    }

    /**
     *  解密
     */
    public static String decrypt(String data,boolean hasPrefix) {
        if (!StringUtil.isNullOrEmpty(data) && data.startsWith(DECRYPT_PREFIX)){
            data = data.substring(9);
            return decrypt(data, PropertiesUtil.getProperty(SECRET_KEY), PropertiesUtil.getProperty(SECRET_IV), hasPrefix);
        }else {
            log.debug("数据为空或数据已解密");
            return data;
        }
    }

    public static void main(String[] args) {
        /*String key = "2618c271-27bb-11e6-bc2c-0242ac110002";
        String message = "1750-4a0ac0bedaa61b15b4809b3bc6576c4a-" + key;
        System.out.println("Message:" + message);
        String encryptedMessage = encrypt(message, key);
        System.out.println("encryptedMessage:" + encryptedMessage);
        String decryptMessage = decrypt(encryptedMessage, key);
        System.out.println("decryptMessage:" + decryptMessage);*/

        String key ="UAA4GNADCBiQKBgQ";
        String iv = "QKBgQCK8RBamw5ba";

        String encryptStr = encrypt(
                "{\"monitorCollectFrequency\":5,\"alarmCollectFrequency\":5,\"envName\":\"云星数据 - RegionOne - RegionOne\",\"providerUrl\":\"http://192.200.200.77:9000/v3\",\"version\":\"V3\",\"company\":\"default\",\"region\":\"RegionOne\",\"envAccount\":\"85368c3c-e3ce-48bc-89f0-933cc39199ed\",\"tenantName\":\"\",\"tenantUserName\":\"cloudstar\",\"tenantUserPass\":\"1q2w3e4r\",\"serverPort\":8000,\"adminFlag\":false,\"envDescription\":\"\"},",
                key, iv);

        System.out.println(encryptStr);
        System.out.println(decrypt(encryptStr,key, iv));
    }
}
