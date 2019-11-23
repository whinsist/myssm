package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;

public class Module implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模块SID
     */
    private String moduleSid;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块URL
     */
    private String moduleUrl;

    /**
     * 模块图标URL
     */
    private String moduleIconUrl;

    /**
     * 父模块SID
     */
    private String parentSid;

    /**
     * 功能权限
     */
    private String permission;

    /**
     * 模块类型(0:目录; 1:菜单;2:按钮)
     */
    private Integer moduleType;

    /**
     * 是否显示 0:否 1:是
     */
    private Integer displayFlag;

    /**
     * 显示顺序
     */
    private Integer sortRank;

    /**
     * 前后台标识
     */
    private String moduleCategory;

    /**
     * 模块功能描述
     */
    private String description;

    /**
     * @return 模块SID
     */
    public String getModuleSid() {
        return moduleSid;
    }

    /**
     * @param moduleSid 
	 *            模块SID
     */
    public void setModuleSid(String moduleSid) {
        this.moduleSid = moduleSid;
    }

    /**
     * @return 模块名称
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName 
	 *            模块名称
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return 模块URL
     */
    public String getModuleUrl() {
        return moduleUrl;
    }

    /**
     * @param moduleUrl 
	 *            模块URL
     */
    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    /**
     * @return 模块图标URL
     */
    public String getModuleIconUrl() {
        return moduleIconUrl;
    }

    /**
     * @param moduleIconUrl 
	 *            模块图标URL
     */
    public void setModuleIconUrl(String moduleIconUrl) {
        this.moduleIconUrl = moduleIconUrl;
    }

    /**
     * @return 父模块SID
     */
    public String getParentSid() {
        return parentSid;
    }

    /**
     * @param parentSid 
	 *            父模块SID
     */
    public void setParentSid(String parentSid) {
        this.parentSid = parentSid;
    }

    /**
     * @return 功能权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission 
	 *            功能权限
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return 模块类型(0:目录; 1:菜单;2:按钮)
     */
    public Integer getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType 
	 *            模块类型(0:目录; 1:菜单;2:按钮)
     */
    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return 是否显示 0:否 1:是
     */
    public Integer getDisplayFlag() {
        return displayFlag;
    }

    /**
     * @param displayFlag 
	 *            是否显示 0:否 1:是
     */
    public void setDisplayFlag(Integer displayFlag) {
        this.displayFlag = displayFlag;
    }

    /**
     * @return 显示顺序
     */
    public Integer getSortRank() {
        return sortRank;
    }

    /**
     * @param sortRank 
	 *            显示顺序
     */
    public void setSortRank(Integer sortRank) {
        this.sortRank = sortRank;
    }

    /**
     * @return 前后台标识
     */
    public String getModuleCategory() {
        return moduleCategory;
    }

    /**
     * @param moduleCategory 
	 *            前后台标识
     */
    public void setModuleCategory(String moduleCategory) {
        this.moduleCategory = moduleCategory;
    }

    /**
     * @return 模块功能描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            模块功能描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}