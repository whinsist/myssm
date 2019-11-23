package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;

public class RoleModule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模块SID
     */
    private String moduleSid;

    /**
     * 角色SID
     */
    private Long roleSid;

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
     * @return 角色SID
     */
    public Long getRoleSid() {
        return roleSid;
    }

    /**
     * @param roleSid 
	 *            角色SID
     */
    public void setRoleSid(Long roleSid) {
        this.roleSid = roleSid;
    }
}