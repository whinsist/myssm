package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request;

import lombok.Data;

@Data
public class UpdateProcessRequest {
    private String oper;
    private String id;
    private String processName;

}