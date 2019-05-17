package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.UuidUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileByteUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.FileUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileDownLoadUtil;
import cn.com.cloudstar.rightcloud.framework.config.UploadConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/attach")
public class AttachController {
    private static final String DEFAULT_DIR = "default";
    private static final String SYSTEM_ATTACHDOWN_PREFIX = "http://localhost:8081/api/v1/attach/down";

    @Autowired
    private UploadConfig uploadConfig;


    private static Map<String, String> base64IconExtMap = new HashMap<>();
    static {
        base64IconExtMap.put("data:image/jpeg;", "jpg");
        base64IconExtMap.put("data:image/x-icon;", "ico");
        base64IconExtMap.put("data:image/gif;", "gif");
        base64IconExtMap.put("data:image/png;", "png");
    }



    @ApiOperation(httpMethod = "POST",value = "上传文件",notes = "需要指定路径")
    @PostMapping("/upload")
    public RestResult post(@RequestParam("file") MultipartFile[] files,
                           @RequestParam(required = false) String saveDir) throws IOException {
        saveDir = StringUtil.isBlank(saveDir) ? DEFAULT_DIR : saveDir;
        List<UploadResponse> result = new ArrayList<>();
        for (MultipartFile file: files) {
//            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String uuid = UuidUtil.getShortUuid();
            String originalFilename = file.getOriginalFilename();
            String fileExt = FileUtil.extName(originalFilename);
            String relativeUrl = saveDir + "/"+ uuid + "." + fileExt;

            // 保存文件
            File destination = new File(uploadConfig.getUploadBasePath() +  "/"+ relativeUrl);
            FileUtils.copyInputStreamToFile(file.getInputStream(), destination);

            // 保存到附件表
            //this.attachmentService.insertSelective(attachment);

            // 返回结果
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setRelativeUrl(relativeUrl);
            uploadResponse.setAttachmentSid(uuid);
            uploadResponse.setNginxViewUrl(uploadConfig.getUploadUrlPrefix()+"/"+relativeUrl);
            uploadResponse.setSystemViewUrl(SYSTEM_ATTACHDOWN_PREFIX+"?relativeUrl="+relativeUrl);
            result.add(uploadResponse);
        }
        return new RestResult(result);
    }

    @GetMapping("/down")
    public void downLoad(@RequestParam(required = false) String relativeUrl,
                         @RequestParam(required = false) String attachmentSid,
                         @Context HttpServletResponse response, HttpServletRequest request) {
        if (StringUtil.isNotBlank(attachmentSid)) {
            //Attachments attachments = attachmentService.selectByPrimaryKey(attachmentSid);
            //FileUtil.fileDownLoad(response, uploadConfig.getUploadBasePath() +"/"+ attachments.getAttachmentUrl(), attachments.getOriginalName());
        } else {
            int lasIndexOf = relativeUrl.lastIndexOf("/");
            String fileName = relativeUrl.substring(lasIndexOf + 1, relativeUrl.length());
            String ext = FileUtil.extName(fileName);


//            FileUtil.fileDownLoad(response, uploadConfig.getUploadBasePath() +"/"+ relativeUrl, fileName);

//            FileResponseUtil.downLoad(response, uploadConfig.getUploadBasePath() +"/"+ relativeUrl, "你好师傅师傅师傅水电费."+ext);

            byte[] fileData = FileByteUtil.file2byte(uploadConfig.getUploadBasePath() + "/" + relativeUrl);
            FileDownLoadUtil.downLoadImage(request, response, "你好师傅师傅师傅水电费."+ext, null, fileData);

        }
    }



    @ApiOperation(httpMethod = "POST",value = "上传文件",notes = "需要指定路径和文件的base64数据")
    @PostMapping("/upload_base64")
    public RestResult postBase64(@RequestBody Base64Data base64DataJson){
        try{
            String base64Data = base64DataJson.getBase64Data();
            String saveDir = StringUtil.isBlank(base64DataJson.getSaveDir()) ? DEFAULT_DIR : base64DataJson.getSaveDir();

            if (StringUtil.isBlank(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }
            String [] base64DataArr = base64Data.split("base64,");
            if (base64DataArr.length != 2) {
                throw new Exception("上传失败，数据不合法");
            }
            String dataPrix = base64DataArr[0];
            String data = base64DataArr[1];

            // 文件格式
            String ext = base64IconExtMap.get(dataPrix.toLowerCase());
            if (StringUtil.isBlank(ext)) {
                throw new Exception("上传图片格式不合法");
            }
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String relativeUrl = saveDir + "/"+ uuid+"."+ext;

            // 因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            // 使用apache提供的工具类操作流
            File destination = new File(uploadConfig.getUploadBasePath()+ "/" + relativeUrl);
            FileUtils.writeByteArrayToFile(destination, bs);

            // 保存到附件表
            //this.attachmentService.insertSelective(attachment);

            // 返回结果
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setRelativeUrl(relativeUrl);
            uploadResponse.setAttachmentSid(uuid);
            uploadResponse.setNginxViewUrl(uploadConfig.getUploadUrlPrefix()+"/"+relativeUrl);
            uploadResponse.setSystemViewUrl(SYSTEM_ATTACHDOWN_PREFIX+"?relativeUrl="+relativeUrl);
            return new RestResult(uploadResponse);
        } catch (Exception e) {
            return new RestResult(RestResult.Status.FAILURE, e.getMessage());
        }
    }



    @ApiModel(value="Base64Data对象")
    public static class Base64Data implements Serializable{
        private String base64Data;
        private String saveDir;

        public String getSaveDir() {
            return saveDir;
        }

        public void setSaveDir(String saveDir) {
            this.saveDir = saveDir;
        }

        public String getBase64Data() {
            return base64Data;
        }

        public void setBase64Data(String base64Data) {
            this.base64Data = base64Data;
        }
    }

    public static class UploadResponse implements Serializable{
        private String relativeUrl;
        private String attachmentSid;
        private String nginxViewUrl;
        private String systemViewUrl;


        public String getNginxViewUrl() {
            return nginxViewUrl;
        }

        public void setNginxViewUrl(String nginxViewUrl) {
            this.nginxViewUrl = nginxViewUrl;
        }

        public String getSystemViewUrl() {
            return systemViewUrl;
        }

        public void setSystemViewUrl(String systemViewUrl) {
            this.systemViewUrl = systemViewUrl;
        }

        public String getAttachmentSid() {
            return attachmentSid;
        }

        public void setAttachmentSid(String attachmentSid) {
            this.attachmentSid = attachmentSid;
        }

        public String getRelativeUrl() {
            return relativeUrl;
        }

        public void setRelativeUrl(String relativeUrl) {
            this.relativeUrl = relativeUrl;
        }
    }
}
