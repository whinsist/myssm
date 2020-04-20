package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;

public class UserOrg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 组织SID
     */
    private Long orgSid;

    /**
     * 用户SID
     */
    private Long userSid;

    /**
     * @return 组织SID
     */
    public Long getOrgSid() {
        return orgSid;
    }

    /**
     * @param orgSid 
	 *            组织SID
     */
    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    /**
     * @return 用户SID
     */
    public Long getUserSid() {
        return userSid;
    }

    /**
     * @param userSid 
	 *            用户SID
     */
    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }
}