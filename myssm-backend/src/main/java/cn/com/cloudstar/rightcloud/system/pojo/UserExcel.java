/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.pojo;

import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelDef;

import java.io.Serializable;

 
public class UserExcel implements Serializable {
    
    @ExcelDef(column = "0", columnName = "用户名", width = 25)
    private String account;

    @ExcelDef(column = "1", columnName = "姓名", width = 25)
    private String realName;

    @ExcelDef(column = "2", columnName = "邮箱", width = 25)
    private String email;

    @ExcelDef(column = "3", columnName = "电话", width = 25)
    private String mobile;



	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
    
     
}
