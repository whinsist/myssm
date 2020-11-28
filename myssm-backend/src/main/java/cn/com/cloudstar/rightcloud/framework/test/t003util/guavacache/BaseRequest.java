package cn.com.cloudstar.rightcloud.framework.test.t003util.guavacache;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 13:26 2020/11/04
 */
@Data
public class BaseRequest {

    private String providerUrl;
    private String tenantName;
    private String tenantUserName;
    private String tenantUserPass;

    public BaseRequest(String providerUrl){
        this.providerUrl = providerUrl;
    }
}
