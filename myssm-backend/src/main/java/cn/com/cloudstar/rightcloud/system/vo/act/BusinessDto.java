package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.List;
import java.util.Map;

/**
 *
 * @author yuz
 * @date 2018/12/17
 */
public class BusinessDto {
    private List<String> businessIds;
    private Map<String, String> businessStatus;

    public BusinessDto() {}

    public BusinessDto(List<String> businessIds, Map<String, String> businessStatus) {
        this.businessIds = businessIds;
        this.businessStatus = businessStatus;
    }

    public List<String> getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(List<String> businessIds) {
        this.businessIds = businessIds;
    }

    public Map<String, String> getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Map<String, String> businessStatus) {
        this.businessStatus = businessStatus;
    }
}
