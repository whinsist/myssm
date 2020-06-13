package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.annotation.EncryptDecryptClass;
import cn.com.cloudstar.rightcloud.framework.common.annotation.EncryptDecryptField;
import cn.com.cloudstar.rightcloud.framework.common.util.AESUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;


/**
 * 加密工具类
 */
@Slf4j
public class EncryptDecryptUtil {

    /**
     * 加密方法
     *
     * @param parameterObject Mybatis入参
     * @param <T>
     */
    public static <T> T encrypt(T parameterObject)
            throws IllegalAccessException, InvocationTargetException {
        if (parameterObject == null) {
            return null;
        }
        Field[] declaredFields = parameterObject.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return parameterObject;
        }
        for (Field field : declaredFields) {
            EncryptDecryptField ef = field.getAnnotation(EncryptDecryptField.class);
            if (Objects.isNull(ef)) {
                continue;
            }
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(parameterObject.getClass(), field.getName());
            if (Objects.isNull(pd)) {
                continue;
            }
            Object value = pd.getReadMethod().invoke(parameterObject);
            if (Objects.isNull(value)) {
                continue;
            }
            String encrypt = AESUtil.encrypt(value.toString());
            log.debug("加密字段:[{}.{}] 的数据, [{} -> {}]", parameterObject.getClass().getSimpleName(), field.getName(), value,
                      encrypt);
            pd.getWriteMethod().invoke(parameterObject, encrypt);
        }

        return parameterObject;
    }

    /**
     * 加密方法
     *
     * @param parameterObject Mybatis入参
     * @param <T>
     */
    public static <T> T encrypt(T parameterObject,boolean hasPrefix)
            throws IllegalAccessException, InvocationTargetException {
        if (parameterObject == null) {
            return null;
        }
        Field[] declaredFields = parameterObject.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return parameterObject;
        }
        for (Field field : declaredFields) {
            EncryptDecryptField ef = field.getAnnotation(EncryptDecryptField.class);
            if (Objects.isNull(ef)) {
                continue;
            }
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(parameterObject.getClass(), field.getName());
            if (Objects.isNull(pd)) {
                continue;
            }
            Object value = pd.getReadMethod().invoke(parameterObject);
            if (Objects.isNull(value)) {
                continue;
            }
            String encrypt = AESUtil.encrypt(value.toString(),hasPrefix);
            log.debug("加密字段:[{}.{}] 的数据, [{} -> {}]", parameterObject.getClass().getSimpleName(), field.getName(), value,
                      encrypt);
            pd.getWriteMethod().invoke(parameterObject, encrypt);
        }

        return parameterObject;
    }

    /**
     * 解密方法
     *
     * @param result Mybatis 返回值，需要判断是否是ArrayList类型
     * @param <T>
     */
    public static <T> T decrypt(T result) throws IllegalAccessException, InvocationTargetException {
        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            if (CollectionUtils.isNotEmpty(resultList)) {
                for (int i = 0; i < resultList.size(); i++) {
                    doDecrypt(resultList.get(i));
                }
            }
        } else {
            doDecrypt(result);
        }
        return result;
    }

    /**
     * 解密方法
     *
     * @param result Mybatis 返回值，需要判断是否是ArrayList类型
     * @param <T>
     */
    public static <T> T decrypt(T result,boolean hasPrefix) throws IllegalAccessException, InvocationTargetException {
        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            if (CollectionUtils.isNotEmpty(resultList)) {
                for (int i = 0; i < resultList.size(); i++) {
                    doDecrypt(resultList.get(i),hasPrefix);
                }
            }
        } else {
            doDecrypt(result,hasPrefix);
        }
        return result;
    }

    private static <T> T doDecrypt(T decrypt) throws InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = decrypt.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return decrypt;
        }
        for (Field field : declaredFields) {
            EncryptDecryptField ef = field.getAnnotation(EncryptDecryptField.class);
            if (Objects.isNull(ef)) {
                continue;
            }
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(decrypt.getClass(), field.getName());
            if (Objects.isNull(pd)) {
                continue;
            }
            Object value = pd.getReadMethod().invoke(decrypt);
            if (Objects.isNull(value)) {
                continue;
            }
            String decryptResult = AESUtil.decrypt(value.toString());
            if (StringUtil.isNullOrEmpty(decryptResult)){
                continue;
            }
            char[] decryptResultChar = decryptResult.toCharArray();
            StringBuffer sb = new StringBuffer();
            for(char c:decryptResultChar){
                if (!String.valueOf("\u0000").equals(String.valueOf(c))){
                    sb.append(c);
                }
            }
            log.debug("解密字段:[{}.{}] 的数据, [{} -> {}]", decrypt.getClass().getSimpleName(), field.getName(), value,
                      decryptResult);
            pd.getWriteMethod().invoke(decrypt, sb.toString());
        }
        return decrypt;
    }

    private static <T> T doDecrypt(T decrypt,boolean hasPrefix) throws InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = decrypt.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return decrypt;
        }
        for (Field field : declaredFields) {
            EncryptDecryptField ef = field.getAnnotation(EncryptDecryptField.class);
            if (Objects.isNull(ef)) {
                continue;
            }
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(decrypt.getClass(), field.getName());
            if (Objects.isNull(pd)) {
                continue;
            }
            Object value = pd.getReadMethod().invoke(decrypt);
            if (Objects.isNull(value)) {
                continue;
            }
            String decryptResult = AESUtil.decrypt(value.toString(),hasPrefix);
            if (StringUtil.isNullOrEmpty(decryptResult)){
                continue;
            }
            char[] decryptResultChar = decryptResult.toCharArray();
            StringBuffer sb = new StringBuffer();
            for(char c:decryptResultChar){
                if (!String.valueOf("\u0000").equals(String.valueOf(c))){
                    sb.append(c);
                }
            }
            log.debug("解密字段:[{}.{}] 的数据, [{} -> {}]", decrypt.getClass().getSimpleName(), field.getName(), value,
                      decryptResult);
            pd.getWriteMethod().invoke(decrypt, sb.toString());
        }
        return decrypt;
    }

    /**
     * 对象需要加密或解密
     **/
    public static boolean needDecryptOrDecrypt(Object object) {
        if (StringUtil.isNullOrEmpty(object)){
            return false;
        }
        Class<?> objectClass = object.getClass();
        EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass,
                                                                                 EncryptDecryptClass.class);
        return Objects.nonNull(encryptDecryptClass);
    }
}
