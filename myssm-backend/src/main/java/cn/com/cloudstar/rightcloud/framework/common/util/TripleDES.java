package cn.com.cloudstar.rightcloud.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class TripleDES {

	private static final Logger logger = LoggerFactory.getLogger(TripleDES.class);
	private static byte[] keyBytes = null;
	private DESedeKeySpec desKeySpec;
	private IvParameterSpec ivSpec;

	public TripleDES() {
		try {
			this.desKeySpec = new DESedeKeySpec(keyBytes);
			this.ivSpec = new IvParameterSpec(new String(keyBytes).substring(0, 8).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initKey(String licenseKey) {
		keyBytes = licenseKey.getBytes();
	}

	/**
	 * @param args
	 *
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		TripleDES.initKey("1111111111111111144444444444444");

		String msg = "envType=A1,A2,TC,HC,A3,VM,OS,ES,FC,VC,RG,99,CO,CA&modules=P,O,MH,AE,MB12&productSN=EMEZ-AYMR-Z3A3-RQJU&copyright=版权信息&expireDate=2029-10-10&physicalCount=50&instanceCount=220&versionInfo=Copyright ? 云星数据（深圳）有限公司&version=高级特别版&licenseFor=云星数据";
		System.out.println("【加密前】：" + msg);

		String content = new TripleDES().encrypt(msg);
		System.out.println("【加密后】：" + content);

		String result = new TripleDES().decrypt(content);
		System.out.println("【解密后】：" + result);
		System.out.println("是否解密成功----" + result.equals(msg));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String content) {
		byte[] cryptedBase64Byte = Base64.getDecoder().decode(content);
		return new String(this.decrypt(cryptedBase64Byte), StandardCharsets.UTF_8);
	}

	public byte[] decrypt(byte[] crypted) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = factory.generateSecret(this.desKeySpec);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, this.ivSpec);
			return cipher.doFinal(crypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}