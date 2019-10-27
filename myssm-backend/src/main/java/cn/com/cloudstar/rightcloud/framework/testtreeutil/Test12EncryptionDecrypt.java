package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import cn.com.cloudstar.rightcloud.framework.common.util.file.FileByteUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileMd5;

public class Test12EncryptionDecrypt {


    public static void main(String[] args) throws Exception {
        //        安全相关工具类
//        加密分为三种：
//        1、对称加密（symmetric），例如：AES、DES等
//        2、非对称加密（asymmetric），例如：RSA、DSA等
//        3、摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等

        testAes();
//        testMd5();
//        testBase64();



    }

    private static void testAes() {
        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        System.out.println("aes加密字节数组："+new String(encrypt));
        byte[] decrypt = aes.decrypt(encrypt);
        System.out.println("aes解密字节数组："+new String(decrypt));

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("aes加密16字符串："+new String(encryptHex));
        //解密为字符串
        System.out.println("aes解密16字符串："+aes.decryptStr(encryptHex));
        
        System.out.println("-------------");
    }

    private static void testBase64() {

        String orginString = "你好啊！@";
        String jiami = Base64.encodeBase64String(orginString.getBytes());
        System.out.println("Base64加密=" + jiami);
        byte[] bytes = Base64.decodeBase64(jiami);
        String jiemi = new String(bytes);
        System.out.println("Base64解密=" + jiemi);
        System.out.println("Base64解密是否成功=" + (orginString).equals(jiemi));

        System.out.println("---------------");
        String jiami2 = Base64Utils.encodeToString(orginString.getBytes());
        System.out.println("Base64Utils加密=" + jiami2);
        System.out.println("Base64Utils解密=" + new String(Base64Utils.decodeFromString(jiami2)));
        System.out.println(
                "Base64Utils解密是否成功=" + (orginString).equals(new String(Base64Utils.decodeFromString(jiami2))));
    }

    private static void testMd5() throws IOException {
        // 字符串md5加密
        String str = DigestUtils.md5Hex("admin");
        System.out.println(str);

        // 文件md5加密
        System.out.println("文件md5---" + FileMd5.getFileMD5(new File("e:/temp/test.txt")));
        System.out.println("文件md5---" + FileMd5.getFileMD5(new FileInputStream("e:/temp/test.txt")));
        System.out.println("文件md5---" + DigestUtils.md5Hex(FileByteUtil.file2byte("e:/temp/test.txt")));
        System.out.println("文件md5---" + DigestUtils.md5Hex(new FileInputStream("e:/temp/test.txt")));
    }

}
