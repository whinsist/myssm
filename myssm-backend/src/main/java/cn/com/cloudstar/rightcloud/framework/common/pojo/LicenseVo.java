package cn.com.cloudstar.rightcloud.framework.common.pojo;


import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 17:02 2018/06/06
 */
@Data
public class LicenseVo {

    private String expireDate;
    private String physicalCount;
    private String instanceCount;
    private String version;
    private String support;
    private String versionInfo;

    /**
     * 授权模块,多个以，分隔
     **/
    private String modules;
    /**
     * 产品硬件特征码，来源于安装机器CMP许可证页面
     **/
    private String productSN;
    /**
     * 版权信息
     **/
    private String copyright;
    /**
     * 支持云环境类型（使用简码），多个以逗号分隔。
     **/
    private String envType;

    /**
     * 授予对象
     **/
    private String licenseFor;

}
