package cn.com.cloudstar.rightcloud.framework.common.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对于两个基本相同的实例进行复制，相对于其他复制操作，效率更高
 * <p>
 * Created on 2017/12/26
 *
 * @author ChaoHong.Mao
 */
public class BeanCopierUtils {
    /**
     * 缓存拷贝类型
     */
    private static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();


    /**
     * Copy properties.
     *
     * @param source the source
     * @param target the target
     */
    public static void copyProperties(Object source, Object target) {

        String beanKey = generateKey(source.getClass(), target.getClass());

        BeanCopier copier;

        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }

        copier.copy(source, target, null);

    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
