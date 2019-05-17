package cn.com.cloudstar.rightcloud.framework.config;

import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Hong.Wu
 * @date: 14:21 2018/08/03
 */
@Component
public class UploadConfig {

    @Value("${upload.base.path}")
    private String uploadBasePath;

    @Value("${upload.url.prefix}")
    private String uploadUrlPrefix;

    public String getUploadBasePath() {
        return uploadBasePath;
    }

    public String getUploadUrlPrefix() {
        return uploadUrlPrefix;
    }

    /**
     * 拼接正式访问url 如果是nginx作服务器的时候直接访问即可
     *
     * @param relativeUrl 相对路径
     * @return
     */
    public String formalUrl(String relativeUrl){
        if (StringUtil.isBlank(relativeUrl)) {
            return null;
        }
        if (!uploadUrlPrefix.endsWith("/") && !relativeUrl.startsWith("/")) {
            return uploadUrlPrefix + "/"+ relativeUrl;
        }
        return uploadUrlPrefix + relativeUrl;
    }
}
