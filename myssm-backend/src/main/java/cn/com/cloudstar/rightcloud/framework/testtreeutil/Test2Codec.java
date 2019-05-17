package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;

import cn.com.cloudstar.rightcloud.framework.common.util.file.FileByteUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileMd5;

public class Test2Codec {
	public static void main(String[] args) throws Exception {
		// 字符串md5加密 
		String str = DigestUtils.md5Hex("admin");
		System.out.println(str);
		
		// 文件md5加密
		System.out.println("文件md5---"+ FileMd5.getFileMD5(new File("e:/temp/test.txt")));
		System.out.println("文件md5---"+FileMd5.getFileMD5(new FileInputStream("e:/temp/test.txt")));
		System.out.println("文件md5---"+ DigestUtils.md5Hex(FileByteUtil.file2byte("e:/temp/test.txt")));
		System.out.println("文件md5---"+ DigestUtils.md5Hex(new FileInputStream("e:/temp/test.txt")));

		// 文件base64加密 解密
//		Base64 base64 = new Base64();
//		String base64Str = base64.encodeToString("测试22222222222".getBytes());
//		System.out.println("base64Str="+base64Str);
//		String originStr = new String(base64.decode(base64Str));
//		System.out.println("解密："+originStr);

		String jiami = Base64.encodeBase64String("你好啊！@".getBytes());
		System.out.println("Base64加密="+jiami);
		byte[] bytes = Base64.decodeBase64(jiami);
		System.out.println("Base64解密=" + new String(bytes));
		System.out.println("==="+("你好啊！@").equals(new String(bytes)));

		boolean f = Base64Utils.encodeToString("你好啊！@".getBytes()).equals(jiami);
		System.out.println("---f--"+ f);


		String url = "http://baidu.com?name=你好";
		String encode = new URLCodec().encode(url);
		System.out.println("url编码--------"+encode);
		String decode = new URLCodec().decode(encode);
		System.out.println("url解码--------"+decode);


		String md5Hex = DigestUtils.md5Hex( "test中国".getBytes("UTF-8"));
		System.out.println("md5Hex--------"+md5Hex);
		String sha1Hex = DigestUtils.sha1Hex( "test中国".getBytes("UTF-8"));
		System.out.println("sha1Hex--------"+sha1Hex);



		
		 
	}
}
