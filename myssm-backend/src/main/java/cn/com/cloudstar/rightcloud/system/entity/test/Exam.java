package cn.com.cloudstar.rightcloud.system.entity.test;

import java.io.Serializable;

public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String regionCd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegionCd() {
        return regionCd;
    }

    public void setRegionCd(String regionCd) {
        this.regionCd = regionCd;
    }
}