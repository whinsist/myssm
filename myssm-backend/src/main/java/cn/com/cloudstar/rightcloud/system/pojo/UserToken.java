/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.pojo;

import java.io.Serializable;

/**
 * Created by qct on 2016/2/25.
 */
public class UserToken implements Serializable {
    private Long id;
    private String uuid;
    private Long userSid;
    private String accessToken;
    private Long accessExpire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserSid() {
        return userSid;
    }

    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getAccessExpire() {
        return accessExpire;
    }

    public void setAccessExpire(Long accessExpire) {
        this.accessExpire = accessExpire;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "UserToken{" + "id=" + id + ", userSid=" + userSid + ", accessToken='" + accessToken + '\'' +
                ", accessExpire=" + accessExpire + '}';
    }
}
