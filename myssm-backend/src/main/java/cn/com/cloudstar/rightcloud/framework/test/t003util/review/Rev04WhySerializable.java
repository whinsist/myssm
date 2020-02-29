package cn.com.cloudstar.rightcloud.framework.test.t003util.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import cn.com.cloudstar.rightcloud.framework.test.t003util.review.bean.CanSeriDto;

/**
 * @author Hong.Wu
 * @date: 22:53 2020/02/24
 */
public class Rev04WhySerializable {

    public static void main(String[] args) throws Exception {
        //第一步：创建一个 JavaBean 对象
        CanSeriDto canSeriDto = new CanSeriDto();
        canSeriDto.setName("test");
        canSeriDto.setAge(12);

        // 第二步：使用 ObjectOutputStream 对象实现序列化
        // 打开 canSeriDto.txt 文件，发现里面的内容乱码，注意这不需要我们来看懂，这是二进制文件，计算机能读懂就行了。
        OutputStream out = new FileOutputStream("e:/temp/canSeriDto.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(canSeriDto);

        // 第三步：使用ObjectInputStream 对象实现反序列化
        // 反序列化的对象必须要提供该对象的字节码文件.class
        InputStream in = new FileInputStream("e:/temp/canSeriDto.txt");
        ObjectInputStream os = new ObjectInputStream(in);
        CanSeriDto p = (CanSeriDto) os.readObject();
        System.out.println(p);
        os.close();

        // 问题1：如果某些数据不需要做序列化，比如密码，比如上面的年龄？
        // 在字段面前加上 transient

        //问题2：序列化版本问题，在完成序列化操作后，由于项目的升级或修改，可能我们会对序列化对象进行修改，比如增加某个字段，那么我们在进行反序列化就会报错：
        // 解决办法：在 JavaBean 对象中增加一个 serialVersionUID 字段，用来固定这个版本，无论我们怎么修改，版本都是一致的，就能进行反序列化了

    }


}
