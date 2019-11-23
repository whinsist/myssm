package cn.com.cloudstar.rightcloud.framework.test.t001study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 7:17 2019/10/27
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls1 = Class.forName("cn.com.cloudstar.rightcloud.framework.test.t001study.reflect.Person");
        Class<Person> personClass = Person.class;
        Class<? extends Person> cls3 = new Person().getClass();

        System.out.println(cls1==cls3);
        System.out.println(cls1==personClass);


        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        Person person = new Person();
        Field field = personClass.getField("a");
        field.set(person, "哈哈");



        Object o = field.get(person);
        System.out.println("o="+o);

        Constructor<Person> constructor = personClass.getConstructor(String.class, Integer.class);
        System.out.println(constructor);
        Person person1 = constructor.newInstance("zhang", 14);
        System.out.println(person1);

        Method eatMethod = personClass.getMethod("eat", String.class);
        Object invoke = eatMethod.invoke(person1, "3333333");
        System.out.println(invoke);

        Method[] methods = personClass.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }
    }
}


@Data
class Person {
    private String name;
    private Integer age;
    public String a;
    public Person(){}
    public Person (String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public String eat(String aa) {
        System.out.println("----------eat---------");
        return this.name +"-"+aa + "-123";
    }

}
