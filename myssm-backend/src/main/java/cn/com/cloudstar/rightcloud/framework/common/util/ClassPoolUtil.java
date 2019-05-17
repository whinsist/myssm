/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author swq
 * @date 2/4/2016
 */
public class ClassPoolUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassPoolUtil.class);

    /**
     * 修改方法的注解属性值
     */
    public static void setMethodAnnotation(Class<?> methodClass, String methodName, Class<?> annotationClass,
                                           String annotationAttr, String newAnnotationValue) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.get(methodClass.getName());
        CtMethod[] cms = ct.getDeclaredMethods();
        for (CtMethod cm : cms) {
            if (cm.getName().equals(methodName)) {
                MethodInfo minInfo = cm.getMethodInfo();
                ConstPool cp = minInfo.getConstPool();
                AnnotationsAttribute newAttribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
                Annotation annotation = new Annotation(annotationClass.getName(), cp);
                annotation.addMemberValue(annotationAttr, new StringMemberValue(newAnnotationValue, cp));
                newAttribute.setAnnotation(annotation);
                minInfo.addAttribute(newAttribute);
            }
        }
    }

    /**
     * 获取方法注解的值
     */
    public static String getMethodAnnotation(String methodClassPath, String methodName, Class<?> annotationClass,
                                             String annotationAttr) throws NotFoundException {
        String value = "";
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.get(methodClassPath);
        CtMethod[] cms = ct.getDeclaredMethods();
        for (CtMethod cm : cms) {
            if (cm.getName().equals(methodName)) {
                MethodInfo minInfo = cm.getMethodInfo();
                AnnotationsAttribute attribute = (AnnotationsAttribute) minInfo
                        .getAttribute(AnnotationsAttribute.visibleTag);
                Annotation annotation = attribute.getAnnotation(annotationClass.getName());
                String anValue = ((StringMemberValue) annotation.getMemberValue(annotationAttr)).getValue();
                value = anValue;
            }
        }
        return value;
    }

}
