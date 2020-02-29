package cn.com.cloudstar.rightcloud.framework.test.t003util.review.bean;

import java.io.Serializable;

/**
 * @author Hong.Wu
 * @date: 22:52 2020/02/24
 */
public class CanSeriDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Integer age;

    /**
     * 增加字段时  如果反序列时要检查serialVersionUID
     */
    private String addField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddField() {
        return addField;
    }

    public void setAddField(String addField) {
        this.addField = addField;
    }

    @Override
    public String toString() {
        return "CanSeriDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addField='" + addField + '\'' +
                '}';
    }
}
