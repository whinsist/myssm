package cn.com.cloudstar.rightcloud.system.pojo.beans;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 8:14 2019/03/24
 */
@Data
public class UserLoginBean {
    private String account;
    private String password;
    private String captchaKey;
    private String captcha;

}
