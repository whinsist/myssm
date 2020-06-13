package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import cn.com.cloudstar.rightcloud.framework.common.util.file.FileByteUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileMd5;

public class Test12EncryptionDecrypt {


    public static void main(String[] args) throws Exception {
        //        安全相关工具类
//        加密分为三种：
//        1、对称加密（symmetric），例如：AES、DES等 所谓对称加密算法:加密和解密使用相同的秘钥的算法
//        2、非对称加密（asymmetric），例如：RSA、DSA等
//        3、摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等

//        https://www.jianshu.com/p/213d69ac27b3
//        testAes();
//        testDes();

//        testRsa();

//        testMd5();

//        testBase64();

        testHex();

    }

    private static void testHex() {
        String str = "我是一个字符串";
        String hex = HexUtil.encodeHexStr(str, CharsetUtil.CHARSET_UTF_8);
        //hex是：
        //e68891e698afe4b880e4b8aae5ad97e7aca6e4b8b2
        System.out.println("hex=" + hex);
        String decodedStr = HexUtil.decodeHexStr(hex);
        System.out.println("decodedStr=" + decodedStr);
    }

    private static void testBase64() {
        String orginString = "你好啊！@";
        String jiami = Base64.encodeBase64String(orginString.getBytes());
        System.out.println("apache Base64加密=" + jiami);
        byte[] bytes = Base64.decodeBase64(jiami);
        String jiemi = new String(bytes);
        System.out.println("apache Base64解密=" + jiemi);
        System.out.println("apache Base64解密是否成功=" + (orginString).equals(jiemi));

        System.out.println("---------------");
        String jiami2 = Base64Utils.encodeToString(orginString.getBytes());
        System.out.println("spring Base64Utils加密=" + jiami2);
        System.out.println("spring Base64Utils解密=" + new String(Base64Utils.decodeFromString(jiami2)));
        System.out.println(
                "spring Base64Utils解密是否成功=" + (orginString).equals(new String(Base64Utils.decodeFromString(jiami2))));

        System.out.println("---------------");
        String encode = cn.hutool.core.codec.Base64.encode(orginString);
        String decodeStr = cn.hutool.core.codec.Base64.decodeStr(encode);
        System.out.println("hutool Base64 加密=" + encode);
        System.out.println("hutool Base64 解密=" + decodeStr);
        System.out.println("hutool Base64 是否正确：" + decodeStr.equals(orginString));
    }

    private static void testMd5() throws IOException {
        String testStr = "test中文";
        // 字符串md5加密
        String str = DigestUtils.md5Hex(testStr);
        System.out.println(str);

        // 文件md5加密
        System.out.println("文件md5---" + FileMd5.getFileMD5(new File("e:/testfiles/test.txt")));
        System.out.println("文件md5---" + FileMd5.getFileMD5(new FileInputStream("e:/testfiles/test.txt")));
        System.out.println("文件md5---" + DigestUtils.md5Hex(FileByteUtil.file2byte("e:/testfiles/test.txt")));
        System.out.println("文件md5---" + DigestUtils.md5Hex(new FileInputStream("e:/testfiles/test.txt")));
        System.out.println("hutool------------");

        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(testStr);
        System.out.println(digestHex);
        // 当然，做为最为常用的方法，MD5等方法被封装为工具方法在DigestUtil中，以上代码可以进一步简化为：
        String md5Hex1 = DigestUtil.md5Hex(testStr);
    }

    private static void testRsa() {
        RSA rsa = new RSA();

        //获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
        //获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        System.out.println(StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
    }

    private static void testDes() {
        String content = "test中文";

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();

        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);

        //加密
        byte[] encrypt = des.encrypt(content);
        //解密
        byte[] decrypt = des.decrypt(encrypt);

        //加密为16进制字符串（Hex表示）
        String encryptHex = des.encryptHex(content);
        //解密为字符串
        String decryptStr = des.decryptStr(encryptHex);
        System.out.println("encryptHex=" + encryptHex);
        System.out.println("decryptStr=" + decryptStr);

    }


    private static void testAes() {
        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        System.out.println("aes加密字节数组：" + new String(encrypt));
        byte[] decrypt = aes.decrypt(encrypt);
        System.out.println("aes解密字节数组：" + new String(decrypt));

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("aes加密16字符串：" + new String(encryptHex));
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("aes解密16字符串：" + decryptStr);

        System.out.println("-------------");
    }


}
