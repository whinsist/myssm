package cn.com.cloudstar.rightcloud.framework.common.util.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileByteUtil {
	
	public static void main(String[] args) {
		byte[] bytes = file2byte("e:/1.png");
		System.out.println(bytes.length); //455328   //查看图片详情----实际大小：444 KB (455,328 字节) 占用空间：448 KB (458,752 字节)
		
		
		byte2File(bytes, "e:/1111.png");
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    public static byte[] file2byte(String filePath){  
        try {  
            File file = new File(filePath);
            return inputStream2Byte(new FileInputStream(file));
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());
        }
    }

    public static byte[] inputStream2Byte(InputStream is){
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static byte[] file2byte2(String filePath){  
    	InputStream is = null;
    	try {  
    		File file = new File(filePath);
    		is = new FileInputStream(file);
    	    byte[] fielByte = new byte[is.available()];
    	    is.read(fielByte); 
    	    return fielByte;
    	} catch (Exception e) {  
    		throw new RuntimeException(e.getMessage());
    	} finally {
    		try {
    			if (is != null) {
    				is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} 
    }
    
    
    
    
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void byte2File(byte[] bfile, String filePath) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        try {  
        	File file = new File(filePath);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
}
