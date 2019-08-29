package cn.com.cloudstar.rightcloud.system.activiti.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author Hong.Wu
 * @date: 15:46 2019/07/22
 */
public class StaticDir {
    /**
     * 支持的流程业务类型
     */
    public static List<Map<String, String>> BUSINESS = Lists.newArrayListWithCapacity(8);

    /**
     * 流程业务类型
     */
    public static Map<String, String> BUSINESS_MAP = Maps.newHashMap();

    static {
        BUSINESS.add(ImmutableMap.of("processCode", "service-apply", "businessName", "服务开通"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-changegrade", "businessName", "服务变更"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-renew", "businessName", "服务续订"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-release", "businessName", "服务退订"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-execScript", "businessName", "执行脚本"));
        BUSINESS.add(ImmutableMap.of("processCode", "ticket", "businessName", "提交工单"));

        for (Map<String, String> business : BUSINESS) {
            BUSINESS_MAP.put(business.get("processCode"), business.get("businessName"));
        }
    }
}
