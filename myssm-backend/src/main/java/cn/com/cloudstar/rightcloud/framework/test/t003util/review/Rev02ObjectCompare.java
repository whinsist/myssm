package cn.com.cloudstar.rightcloud.framework.test.t003util.review;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Objects;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Hong.Wu
 * @date: 21:47 2019/08/28
 *
 * 对象比较为什么要重写hashcod equals
 *
 * https://www.cnblogs.com/dumengzhen/p/10562951.html
 */
public class Rev02ObjectCompare {

    public static void main(String[] args) {

        Student s1 = new Student("小明", 18);
        Student s2 = new Student("小明", 18);
        System.out.println("不重写equals 返回false----------" + (s1.equals(s2)));
        System.out.println("重写equals 返回true----------" + (s1.equals(s2)));

        /**
         * ps.总结：对于这个问题，是比较容易被忽视的，曾经同时趟过这坑，Map中存了2个数值一样的key，所以大家谨记哟！ 在重写equals方法的时候，一定要重写hashCode方法。
          最后一点：有这个要求的症结在于，要考虑到类似HashMap、HashTable、HashSet的这种散列的数据类型的运用。
         */
        Map<Student, String> dataMap = new HashMap<>();
        dataMap.put(s1, "s1");
        dataMap.put(s2, "s1");
        System.out.println("不重写hashCode返回null----------"+dataMap.get(new Student("小明", 18)));
        System.out.println("不重写hashCode返回s1----------"+dataMap.get(new Student("小明", 18)) +"  size=" + dataMap.size());




        // List是有序可以重复，Set是无序不可以重复 所有要判断s1与s2是否相等
        Set<Student> set = new HashSet();
        set.add(s1);
        set.add(s2);
        System.out.println(JSON.toJSONString(set));



        List<Long> test1 = new ArrayList<>();
        test1.add(1L);
        test1.add(2L);
        System.out.println(test1.hashCode());  //994
        test1.set(0,2L);
        System.out.println(test1.hashCode());  //1025

        Map<HashMapTest, Integer> map = new HashMap<HashMapTest, Integer>();
        HashMapTest instance = new HashMapTest(1);
        System.out.println("instance.hashcode:" + instance.hashCode());
        map.put(instance, 1);
        HashMapTest newInstance = new HashMapTest(1);
        System.out.println("newInstance.hashcode:" + newInstance.hashCode());
        Integer value = map.get(newInstance);
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("value is null");
        }
    }

}


class HashMapTest {
    private int a;

    public HashMapTest(int a) {
        this.a = a;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        Integer $data = this.getA();
        int result1 = result * 59 + ($data == null?43:$data.hashCode());
        return result1;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof HashMapTest)) {
            return false;
        } else {
            HashMapTest other = (HashMapTest)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                Integer this$data = this.getA();
                Integer other$data = other.getA();
                if(this$data == null) {
                    if(other$data != null) {
                        return false;
                    }
                } else if(!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof HashMapTest;
    }



    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getA() {
        return a;
    }

}

class Student {

    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equal(getName(), student.getName()) &&
                Objects.equal(getAge(), student.getAge());

        // Object:
//        public boolean equals(Object obj) {
//            return (this == obj);
//        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getAge());
//        return HashCodeBuilder.reflectionHashCode(this);
        // Object:
        // public native int hashCode();
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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


}