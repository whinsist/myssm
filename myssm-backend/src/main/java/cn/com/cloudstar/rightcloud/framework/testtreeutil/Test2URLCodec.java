package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

public class Test2URLCodec {
	public static void main(String[] args) throws Exception {


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
