package cn.com.cloudstar.rightcloud.framework.test.t003util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.pojo.TestBean;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;

/**
 * @author Hong.Wu
 * @date: 21:17 2019/06/08
 */
public class Test6Javabeans {

    public static void main(String[] args) throws IntrospectionException {

        //或得bean类
        BeanInfo beninfo = Introspector.getBeanInfo(TestBean.class);
        //获得所有的属性描述
        PropertyDescriptor[] propertydescriptors = beninfo.getPropertyDescriptors();
        for(PropertyDescriptor pd:propertydescriptors){
            //获得读写方法
            System.out.println(pd.getName());
            System.out.println((pd.getReadMethod() != null ? pd.getReadMethod().getName() :"") + " - "
                                       + (pd.getWriteMethod() !=null ? pd.getWriteMethod().getName() : ""));

        }

//        ReflectUtil


        TestBean testBean = new TestBean();
        testBean.setId(123L);
        testBean.setName("aaa");
        testBean.setBirithday(new Date());
        Map<String, Object> map = BeanUtil.transBean2Map(testBean);
        System.out.println(map);

        TestBean bean2 = new TestBean();
        BeanUtil.transMap2Bean(map, bean2);
        System.out.println("bean2="+bean2);

//        BeanUtils.copyProperties();


    }

}
