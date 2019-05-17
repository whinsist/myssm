package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.api.ApiGroupEnum;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation(httpMethod = "GET", value = "Api分组信息",notes = "提供给swagger-ui页面使用的，返回的是api分组数据")//swagger 当前接口注解
    @GetMapping(value = "/groups",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonNode swaggerGroups(){
        List<Map<String,String>> result = new ArrayList<>();
        for (ApiGroupEnum apiGroupEnum : ApiGroupEnum.values()) {
            Map<String,String> data = new HashMap<>();
            String url = "/api/v1/v2/api-docs?group="+apiGroupEnum.getPath();
            data.put("url",url);
            data.put("name",apiGroupEnum.getGroupName());
            result.add(data);
        }
        return JsonUtil.fromJson(JsonUtil.toJson(result));
    }


    @GetMapping(value = "/download/{groupPath}")
    public void swaggerDownload(@PathVariable String groupPath, HttpServletRequest request, HttpServletResponse res) throws MalformedURLException, UnsupportedEncodingException {
       /* String contextpath = request.getScheme() +"://" + request.getServerName()  + ":" +request.getServerPort() +request.getContextPath();

        URL remoteSwaggerFile = new URL(contextpath+"/v1/v2/api-docs?group="+groupPath);

        Swagger2MarkupConfigBuilder configBuilder = new Swagger2MarkupConfigBuilder();
        configBuilder.withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS);

//        configBuilder.withMarkupLanguage(MarkupLanguage.ASCIIDOC);
//        configBuilder.withSwaggerMarkupLanguage(MarkupLanguage.ASCIIDOC);

        configBuilder.withMarkupLanguage(MarkupLanguage.MARKDOWN);
        configBuilder.withSwaggerMarkupLanguage(MarkupLanguage.MARKDOWN);

        Path outputDirectory = Paths.get("/tmp/tmp/swagger");
        Swagger2MarkupConfig config = configBuilder.build();
        Swagger2MarkupConverter.from(remoteSwaggerFile)
                .withConfig(config)
                .build()
                .toPath(outputDirectory);
//        String adocFileName = outputDirectory.toString()+".adoc";
//
//        Asciidoctor asciidoctor = create();
//        Map<String,Object> attributes = new HashMap<>();
//        attributes.put(Attributes.SOURCE_LANGUAGE,"zh");
//
//        Map<String, Object> options = options()
//                .inPlace(true)
//                .backend("pdf")
//                .attributes(attributes)
//                .asMap();
//        String outfile = asciidoctor.convertFile(new File(adocFileName), options);
        String groupName ="";
        for (ApiGroupEnum groupEnum : ApiGroupEnum.values()) {
            if(groupEnum.getPath().equals(groupPath)){
                groupName = groupEnum.getGroupName();
            }
        }

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(groupName+"api文档.md","UTF-8"));
        res.setCharacterEncoding("UTF-8");

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(outputDirectory.toString()+".md")));
            org.apache.commons.io.IOUtils.copy(bis, res.getOutputStream());
            res.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileUtil.deleteFile("/tmp/tmp");
        }*/
    }

    private enum File_sufix{
        MARKDOWN("markdown"),
        PDF("pdf"),
        ASCIIDOC("ascii"),
        TXT("txt");
        private String name ;
        File_sufix(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
