package cn.com.cloudstar.rightcloud.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;


public class TripleDES {
	private static final Logger logger = LoggerFactory.getLogger(TripleDES.class);

	private DESedeKeySpec desKeySpec;
	private IvParameterSpec ivSpec;
	private static byte[] keyBytes = null;

	public static void initKey(String licenseKey){
		keyBytes = licenseKey.getBytes();
	}

	public TripleDES() {
		try {
			this.desKeySpec = new DESedeKeySpec(keyBytes);
			this.ivSpec = new IvParameterSpec(new String(keyBytes).substring(0, 8).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String msg) {
		byte[] encrypt = this.encrypt(msg.getBytes());
		return Base64.getEncoder().encodeToString(encrypt);
	}
	public byte[] encrypt(byte[] origData) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = factory.generateSecret(this.desKeySpec);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, this.ivSpec);
			return cipher.doFinal(origData);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String decrypt(String content) {
		byte[] cryptedBase64Byte = Base64.getDecoder().decode(content);
		return new String(this.decrypt(cryptedBase64Byte));
	}
	public byte[] decrypt(byte[] crypted) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = factory.generateSecret(this.desKeySpec);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, this.ivSpec);
			return cipher.doFinal(crypted);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TripleDES des = new TripleDES();
		String msg = "expireDate=2018-06-16&physicalCount=100&instanceCount=100&version=企业版";
		System.out.println("【加密前】：" + msg);

		String content = des.encrypt(msg);
		System.out.println("【加密后】：" + content);

		String result = des.decrypt(content);
		System.out.println("【解密后】：" + result);
		System.out.println("是否解密成功----" + result.equals(msg));
	}

}