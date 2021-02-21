package cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author Hong.Wu
 * @date: 9:54 2020/04/01
 */
@Data
public class ExBean {

    private int id;
    private String name;
    private int age;
    private Date birth;
    private String idcard;


    public ExBean() {
    }

    public ExBean(int id, String name, int age, Date birth, String idcard) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.idcard = idcard;
    }


}
