package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:28 2019/06/08
 */
public class TestBean {
    private Long id;
    private String name;
    private Date birithday;

    private String createBy;
    private Date createDt;
    private String updateBy;
    private Date updateDt;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirithday() {
        return birithday;
    }

    public void setBirithday(Date birithday) {
        this.birithday = birithday;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birithday=" + birithday +
                ", createBy='" + createBy + '\'' +
                ", createDt=" + createDt +
                ", updateBy='" + updateBy + '\'' +
                ", updateDt=" + updateDt +
                ", version=" + version +
                '}';
    }
}
