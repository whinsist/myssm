package cn.com.cloudstar.rightcloud.framework.common.study.generic;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

import io.swagger.models.auth.In;

/**
 * @author Hong.Wu
 * @date: 19:10 2019/06/08
 */
public class GeneratorRead {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("str1");
        list.add("test");
        //list.add(100);

        for (int i = 0; i < list.size(); i++) {
            // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
            //此时list默认的类型为Object类型。在之后的循环中，由于忘记了之前在list中也加入了Integer类型的值或其他编码原因，
            //很容易出现类似例子中ClassCastException的错误。因为编译阶段正常，而运行时会出现“java.lang.ClassCastException”异常。因此，导致此类错误编码过程中不易发现。
            // 那么有没有什么办法可以使集合能够记住集合内元素各类型，且能够达到只要编译时不出现问题，运行时就不会出现“java.lang.ClassCastException”异常呢？答案就是使用泛型。
            String name = (String) list.get(i);


            //泛型，即“参数化类型”。一提到参数，最熟悉的就是定义方法时有形参，然后调用此方法时传递实参。那么参数化类型怎么理解呢？顾名思义，就是将类型由原来的具体的类型参数化，类似于方法中的变量参数，此时类型也定义成参数形式（可以称之为类型形参），然后在使用/调用时传入具体的类型（类型实参）。

            //泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型来控制形参具体限制的类型）。也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法。
            List<String> list2 = new ArrayList<String>();

            System.out.println("name:" + name);
        }

        // 泛型只在编译阶段有效   getClass() 返回此 Object 的运行时类。
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        System.out.println("classStringArrayList="+classStringArrayList+"  classIntegerArrayList="+classIntegerArrayList
        + (classStringArrayList.equals(classIntegerArrayList) ? "输入结果：类型相同": "no"));



        // 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法。

        //@@1@@泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Box<Integer> box = new Box<Integer>(123);
        box.getKey();
        Box<String> box2 = new Box<String>("123");
        box2.getKey();

        //定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用。
        //如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。下面的例子编译没有问题，获取对象的value也正确。
        //Box box = new Box("string");
        //Box box1 = new Box(1234);


        //@@2@@泛型接口
        //泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中，可以看一个例子
        FruitGenerator fruitGenerator = new FruitGenerator();
        System.out.println(fruitGenerator.getNext());

        Box<Number> name = new Box<Number>(99);
        Box<Integer> age = new Box<Integer>(712);
        System.out.println("data1 :" + name.getKey());
        System.out.println("data2 :" + age.getKey());

    }   


}

//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
class Box<T> {
    //key这个成员变量的类型为T,T的类型由外部指定

    private T key;

    //泛型构造方法形参key的类型也为T，T的类型由外部指定
    public Box (T key) {
        this.key = key;
    }

    public T getKey (){
        return key;
    }


}

//定义一个泛型接口
interface Generator<T> {
    //接口方法
    T getNext();
}

class FruitGenerator<T> implements Generator<T> {


    @Override
    public T getNext() {
        return null;
    }
}