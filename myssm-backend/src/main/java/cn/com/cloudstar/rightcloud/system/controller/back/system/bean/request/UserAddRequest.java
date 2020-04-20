package cn.com.cloudstar.rightcloud.system.controller.back.system.bean.request;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 20:39 2019/05/17
 */
@Data
public class UserAddRequest {

    private String realName;
    private String mobile;
    private String account;
    private String password;
    private String userType;
    private String sex;

    private Long orgSid;
    private Long roleSid;



}
