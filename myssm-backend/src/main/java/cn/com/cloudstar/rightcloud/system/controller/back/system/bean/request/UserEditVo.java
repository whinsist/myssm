package cn.com.cloudstar.rightcloud.system.controller.back.system.bean.request;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 20:39 2019/05/17
 */
@Data
public class UserEditVo {
    private String oper;
    private String id;
    private Long userSid;
    private String realName;
    private String mobile;
    private String status;

}
