package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 21:28 2019/06/08
 */
public class TestBean {
    private Long id;
    private String name;
    private Date birithday;

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

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birithday=" + birithday +
                '}';
    }
}
