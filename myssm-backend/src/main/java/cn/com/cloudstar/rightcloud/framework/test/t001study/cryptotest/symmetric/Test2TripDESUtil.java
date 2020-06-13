package cn.com.cloudstar.rightcloud.framework.test.t001study.cryptotest.symmetric;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Test2TripDESUtil {

    private static final String KEY_ALGORITHM = "DESede";
    //默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * DESede 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     *
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * DESede 解密操作
     *
     * @param content
     * @param key
     */
    public static String decrypt(String content, String key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(final String key) {
        try {
            //返回生成指定算法密钥生成器的 KeyGenerator 对象
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // DESede
            kg.init(new SecureRandom(key.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为DESede专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = encrypt(content, key);
        System.out.println("s1:" + s1);
        System.out.println("s2:" + decrypt(s1, key));

    }

}