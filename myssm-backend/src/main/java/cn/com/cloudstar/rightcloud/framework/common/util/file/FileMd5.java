package cn.com.cloudstar.rightcloud.framework.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileMd5 {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(getFileMD5(new File("e:/temp/test.txt")));
		
		InputStream in = new FileInputStream("e:/temp/test.txt");
		System.out.println(getFileMD5(in));
		//1c104b9c0accfca52ef21728eaf01453
	}
	
	public static String getFileMD5(InputStream inputStream) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte buffer[] = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			inputStream.close();
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get MD5 of one file:hex string,test OK!
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		try {
			return getFileMD5(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/***
	 * Get MD5 of one file！test ok!
	 * 
	 * @param filepath
	 * @return
	 */
	public static String getFileMD5(String filepath) {
		File file = new File(filepath);
		return getFileMD5(file);
	}
	
	

	 
}
