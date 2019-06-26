package cn.com.cloudstar.rightcloud.framework.common.util.file;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cloudstar.rightcloud.framework.common.constants.ContentTypeConstants;

public class FileDownLoadUtil {


	/**
	 *  下载图片或文件
	 * @param request
	 * @param response
	 * @param fileName
	 * @param contentType
	 * @param fileData  如果是输入流则转为byte数组FileByteUtil.inputStream2Byte(is)
	 * @throws Exception
	 */
	public static void downLoadImageOrFile(HttpServletRequest request, HttpServletResponse response, String fileName, String contentType, byte[] fileData){
		OutputStream out = null;
		try {
            if (StringUtils.isBlank(contentType)) {
                if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
                    contentType = ContentTypeConstants.IMAGE;
                } else {
                    contentType = ContentTypeConstants.STREAM;
                }
            }
            // 对fileName编码
            fileName = getFileNameByAgent(request.getHeader("USER-AGENT"), fileName);

            response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentLength(fileData.length);
			out = response.getOutputStream();
			out.write(fileData);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileNameByAgent(String agent, String fileName) throws UnsupportedEncodingException {
		if (null != agent) {
			if (-1 != agent.indexOf("MSIE") ||  -1 != agent.indexOf("Trident")) {
				// ie
				fileName = URLEncoder.encode(fileName, "UTF8");
			} else if (-1 != agent.indexOf("Mozilla")) {
				// 火狐,chrome等
				fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
			}
		}
		return fileName;
	}



	/********************************************************************
	 *
	 * 面是通过filePath的方式下载
	 *
	 ********************************************************************/
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param filePath
	 */
	public static void downLoad(HttpServletRequest request, HttpServletResponse response, String filePath) {
		downLoad(request, response, filePath, filePath.substring(filePath.lastIndexOf("\\") + 1));
	}

	public static void downLoad(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
		downLoad(request, response, filePath, fileName, ContentTypeConstants.STREAM);
	}

	/**
	 * 1、输出浏览器不同 要进行中文编码
	 * 2、设置content-type为二进制流 默认为二进制流就可以了不用特殊设置
	 * 3、设置content-disposition attachment不想直接显示内容，而是弹出一个"文件下载"的对话框
	 * @param request
	 * @param response
	 * @param filePath 文件全路径 如:"E:\\temp\\测试.pdf"
	 * @param fileName 文件名包括文件格式 如:测试信息.pdf
	 */
	public static void downLoad(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName, String contentType) {
		InputStream in = null;
		OutputStream out = null;
		try {
			fileName = getFileNameByAgent(request.getHeader("USER-AGENT"), fileName);
			// 告诉浏览器当前的响应体是个什么类型的数据response.setContentType==response.setHeader("content-Type"
			// 只要设置成二进制流都可以 不管是pdf或xls
			if (StringUtils.isBlank(contentType)) {
				contentType = ContentTypeConstants.STREAM;
			}
			response.setContentType(contentType);
			// Content-Disposition 的作用：当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
			// inline：建议浏览器使用默认的行为处理响应体。 attachment：建议浏览器将响应体保存到本地，而不是正常处理响应体。
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setHeader("Content-Length", String.valueOf(new File(filePath).length()));

			in = new BufferedInputStream(new FileInputStream(filePath));
			out = response.getOutputStream();
			byte[] buff = new byte[2048];
			int len;
			while ((len = in.read(buff)) !=  -1) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buff, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}