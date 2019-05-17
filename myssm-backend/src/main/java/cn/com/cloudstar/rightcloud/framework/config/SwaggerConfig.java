package cn.com.cloudstar.rightcloud.framework.config;


import cn.com.cloudstar.rightcloud.framework.common.api.ApiGroup;
import cn.com.cloudstar.rightcloud.framework.common.api.ApiGroupEnum;
import cn.com.cloudstar.rightcloud.framework.common.constants.SysConfigConstants;
import cn.com.cloudstar.rightcloud.framework.common.util.PropertiesUtil;
import com.google.common.base.Predicate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.RequestHandler;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Method;
import java.util.ArrayList;


//@Configuration
//@EnableSwagger2
//@ComponentScan(basePackages = {"cn.com.cloudstar.rightcloud"})
//@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

    // online api 文档开关 默认开启 线上环境注入参数关闭
    private boolean swaggerEnable = true;

    SwaggerConfig (){
//        String enbable = System.getProperty(SysConfigConstants.SWAGGER_ENABLE);
        String enbable = PropertiesUtil.getProperty(SysConfigConstants.SWAGGER_ENABLE);
        if(!StringUtils.isEmpty(enbable) && "false".equals(enbable)){
            swaggerEnable = false;
        }

    }
    private ApiInfo apiInfo(String group) {
        Contact contact = new Contact("rightCloud", "https://www.rightcloud.com.cn/", "biz@cloud-star.com.cn");
        return new ApiInfo("rightCloud "+group+"接口",
                group,
                "v1",
                "https://www.rightcloud.com.cn",
                contact,
                "",
                "",new ArrayList<>());
    }
    private Predicate<RequestHandler> getSelector(ApiGroupEnum apiGroupEnum){
        return (input)-> {
            Method method = input.getHandlerMethod().getMethod();
            Class clazz = method.getDeclaringClass();
            if(clazz.isAnnotationPresent(ApiGroup.class)){
                ApiGroup apiGroup = (ApiGroup) clazz.getDeclaredAnnotation(ApiGroup.class);
                if(apiGroupEnum.getPath().equals(apiGroup.value().getPath())){
                    return true;
                }
            }
            return false;
        };
    }

    @Bean
    public Docket allDocket() {
        ApiGroupEnum apiGroupEnum = ApiGroupEnum.ALL;
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(apiGroupEnum.getGroupName()))
                .enable(swaggerEnable)
                .groupName(apiGroupEnum.getPath())
                .alternateTypeRules(AlternateTypeRules.newRule(cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult.class,RestResult.class))
                .useDefaultResponseMessages(false);;
        return docket;
    }

    @Bean
    public Docket auditDocket() {
        return groupDocketBuild(ApiGroupEnum.AUDIT_GROUP);
    }

    @Bean
    public Docket selfDocket() {
        return groupDocketBuild(ApiGroupEnum.SELF_SERVICE_GROUP);
    }

    @Bean
    public Docket resourceDocket() {
        return groupDocketBuild(ApiGroupEnum.RESOURCE_GROUP);
    }

    @Bean
    public Docket monitorDocket() {
        return groupDocketBuild(ApiGroupEnum.MONITOR_GROUP);
    }

    @Bean
    public Docket maintenaceDocket() {
        return groupDocketBuild(ApiGroupEnum.MAINTENANCE_GROUP);
    }

    @Bean
    public Docket systemDocket() {
        return groupDocketBuild(ApiGroupEnum.SYSTEM_GROUP);
    }

    @Bean
    public Docket operationDocket() {
        return groupDocketBuild(ApiGroupEnum.OPERATION_GROUP);
    }


    /**
     * 根据apiGroupEnum创建Docket对象
     * @param apiGroupEnum ApiGroupEnum分组
     * @return
     */
    public Docket groupDocketBuild(ApiGroupEnum apiGroupEnum){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(apiGroupEnum.getGroupName()))
                .select()
                .apis(getSelector(apiGroupEnum))
                .build()
                .enable(swaggerEnable)
                .groupName(apiGroupEnum.getPath())
                .alternateTypeRules(AlternateTypeRules.newRule(cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult.class,RestResult.class))
                .useDefaultResponseMessages(false);
        return docket;
    }


    /**
     * 用于替代RestApi返回
     */
    @ApiModel("Rest返回模型")
    private static class RestResult{

        @ApiModelProperty(value = "页面码",example = "200")
        public int code;

        @ApiModelProperty(value = "拓展数据")
        public Object data;

        @ApiModelProperty(value = "状态",example = "true")
        public boolean status;

        @ApiModelProperty(value = "消息",example = "操作完成")
        public String message;
    }

}