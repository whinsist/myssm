package cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean;

import java.util.Date;
import java.util.Objects;

/**
 * @author Hong.Wu
 * @date: 9:54 2020/04/01
 */
public class ExBean {

    private int id;
    private String name;
    private int age;
    private Date birth;

    public ExBean() {
    }

    public ExBean(int id, String name, int age, Date birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
