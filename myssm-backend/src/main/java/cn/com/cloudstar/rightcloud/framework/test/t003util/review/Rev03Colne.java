package cn.com.cloudstar.rightcloud.framework.test.t003util.review;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;

/**
 * @author Hong.Wu
 * @date: 22:16 2020/02/24
 */
public class Rev03Colne {

    public static void main(String[] args) {
        try {
            Person p1 = new Person("郭靖", 33, new Car("Benz", 300));

            // 浅克隆
            Person pcloneQian = p1;
            pcloneQian.getCar().setBrand("BYD");
            System.out.println("浅克隆发现原来的对象也变化了=" + p1);



            // 深度克隆
            Person p2 = new Person("郭靖测试", 33, new Car("Benz", 300));
            Person pcloneDeep = MyUtil.clone(p2);
            pcloneDeep.getCar().setBrand("HAHA");
            System.out.println("深度克隆发现原来的对象不会变化=" + p2);

            String json = JSON.toJSONString(p2);
            System.out.println(json);
            Person person = JSON.parseObject(json, Person.class);
            person.setName("xxx");

            System.out.println(p2);
            System.out.println(person);

            // json序列化可以强转  btye不能强转
            Car car = JSON.parseObject(json, Car.class);
            System.out.println(car);
            Car carx = (Car) BeanUtil.deserialize(BeanUtil.serialize(p2));
            System.out.println(carx);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private String brand;
    private int maxSpeed;

    public Car(){}
    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}

class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private Car car;
    public Person(){}
    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}

class MyUtil {

    private MyUtil() {
        throw new AssertionError();
    }


    public static <T extends Serializable> T clone(T obj)
            throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream
        //或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，
        //这一点不同于对外部资源（如文件流）的释放
    }
}
