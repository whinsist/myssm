package cn.com.cloudstar.rightcloud.framework.common.util.file;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileDownLoadUtil {

    /**
     *  下载图片或文件
     * @param request
     * @param response
     * @param fileName
     * @param contentType
     * @param fileData
     * @throws Exception
     */
	public static void downLoadImage(HttpServletRequest request, HttpServletResponse response, String fileName, String contentType, byte[] fileData){
		OutputStream out = null;
		try {
            if (StringUtils.isBlank(contentType)) {
                if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
                    contentType = "image/jpeg";
                } else {
                    contentType = "application/octet-stream";
                }
            }
            // 对fileName编码
            fileName = getFileNameByAgent(request.getHeader("USER-AGENT"), fileName);


            response.setContentType(contentType);
            response.setContentLength(fileData.length);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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

	private static String getFileNameByAgent(String agent, String fileName) throws UnsupportedEncodingException {
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


	/**
	 *
	 * @param response
	 * @param filePath 文件全路径
	 * @param fileName 出文件名包括文件格式
	 */
	public static void downLoad(HttpServletResponse response, String filePath, String fileName) {
		InputStream in = null;
		OutputStream out = null;
		try {
			//fileName = getFileNameByAgent(request.getHeader("USER-AGENT"), fileName);
			// 告诉浏览器当前的响应体是个什么类型的数据
			response.setContentType("application/octet-stream");
			// response.setContentType("application/x-msdownload;");
			// Content-Disposition 的作用：当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
			// inline：建议浏览器使用默认的行为处理响应体。 attachment：建议浏览器将响应体保存到本地，而不是正常处理响应体。
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setHeader("Content-Length", String.valueOf(new File(filePath).length()));
			in = new FileInputStream(filePath);
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


	//
	public static void downLoad(HttpServletResponse response, byte[] bytes, String fileName) {
		OutputStream out = null;
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("UTF-8");
			out = response.getOutputStream();
			out.write(bytes);
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

}