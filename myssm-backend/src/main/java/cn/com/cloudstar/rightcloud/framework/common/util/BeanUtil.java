/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.exception.PropertyAccessException;
import com.google.common.base.Strings;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title: Bean util</p>
 * <p>Description: </p>
 *
 * @author Chaohong.Mao
 * @Description
 */
public final class BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 设定 bean property.
     *
     * @param bean     the bean
     * @param property the property
     * @param value    the value
     * @throws PropertyAccessException the property access exception
     */
    public static void setBeanProperty(Object bean, String property, Object value) throws PropertyAccessException {

        try {
            PropertyUtils.setProperty(bean, property, value);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
    }

    /**
     * 获得 bean property type.
     *
     * @param bean     the bean
     * @param property the property
     * @return bean property type
     * @throws PropertyAccessException the property access exception
     */
    public static Class getBeanPropertyType(Object bean, String property) throws PropertyAccessException {
        try {
            Class type = null;
            if (bean instanceof DynaBean) {
                DynaProperty descriptor = ((DynaBean) bean).getDynaClass().getDynaProperty(property);
                if (descriptor != null) {
                    type = descriptor.getType();
                }
            } else {
                type = PropertyUtils.getPropertyType(bean, property);
            }
            return type;
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
    }

    /**
     * Get property from list string [ ].
     *
     * @param <T>          the type parameter
     * @param srcList      the src list
     * @param propertyName the property name
     * @return string [ ]
     * @Description
     */
    public static <T> String[] getPropertyFromList(List<T> srcList, String propertyName) {
        if (srcList == null || srcList.size() == 0) {
            return null;
        }

        String[] des = new String[srcList.size()];
        try {
            for (int i = 0; i < srcList.size(); i++) {
                des[i] = getBeanProperty(srcList.get(i), propertyName).toString();
            }

        } catch (PropertyAccessException e) {
            e.printStackTrace();
        }

        return des;
    }

    /**
     * 获得 bean property.
     *
     * @param bean     the bean
     * @param property the property
     * @return bean property
     * @throws PropertyAccessException the property access exception
     */
    public static Object getBeanProperty(Object bean, String property) throws PropertyAccessException {

        Object value = null;
        try {
            value = PropertyUtils.getProperty(bean, property);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PropertyAccessException(e);
        }
        return value;
    }

    /**
     * 对实体Bean进行序列化操作.
     */
    public static byte[] serialize(Object source) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream ObjOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            ObjOut = new ObjectOutputStream(byteOut);
            ObjOut.writeObject(source);
            ObjOut.flush();
        } catch (IOException e) {
            logger.error(source.getClass().getName() + " serialized error !", e);
        } finally {
            try {
                if (null != ObjOut) {
                    ObjOut.close();
                }
            } catch (IOException e) {
                ObjOut = null;
            }
        }
        return byteOut.toByteArray();
    }

    /**
     * 反序列化bean
     */
    public static Object deserialize(byte[] source) {
        ObjectInputStream ObjIn = null;
        Object retVal = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
            ObjIn = new ObjectInputStream(byteIn);
            retVal = ObjIn.readObject();
        } catch (Exception e) {
            logger.error("deserialized error  !", e);
        } finally {
            try {
                if (null != ObjIn) {
                    ObjIn.close();
                }
            } catch (IOException e) {
                ObjIn = null;
            }
        }
        return retVal;
    }

    /**
     * map转为bean
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    // 得到property对应的setter方法
                    property.getWriteMethod().invoke(obj, map.get(key));
                }
            }
        } catch (Exception e) {
            throw new BizException("map convert bean error ", e);
        }
    }

    /**
     * bean转为map
     * @param obj
     * @return
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        try {
            Map<String, Object> map = new HashMap<>();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Object value = property.getReadMethod().invoke(obj);
                    map.put(key, value);
                }
            }
            return map;
        } catch (Exception e) {
            throw new BizException("bean convert map error: ", e);
        }
    }

    public static void main(String args[]) {
        String a = Strings.padEnd("12345678asdfadsasdfwef23fef哈哈哈哈90qwertyuio", 10, 'a');
        System.out.println(a);
        System.out.println(StringUtil.toMD5(Base64Utils.encodeToString(a.getBytes())));

    }

}
