package cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;


/**
 * 用来填充 命令行参数的
 * @author yangsen
 */
public class CmdParamsFiller {

    public static String fillSingle(String cmd,String key,String value){
        cmd = cmd.replaceAll("\\$\\{"+key+"}", value);
        return cmd;
    }
    public static String fill(String cmd,Object paramter,Map<String,Object> map){
        cmd = fill(cmd,paramter);
        cmd = fill(cmd,map);
        return cmd;
    }
    public static String fill(String cmd, Object paramter){
        Class clazz = paramter.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            String fieldName = field.getName();
            try {
                Method method = clazz.getMethod("get" + StringUtil.upperFirst(fieldName), null);
                Object value = method.invoke(paramter,null);
                cmd = cmd.replaceAll("\\$\\{"+fieldName+"}", String.valueOf(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cmd;
    }
    public static String fill(String cmd, Map<String,Object> paramter){
        Set<Map.Entry<String, Object>> entrySet = paramter.entrySet();
        for (Map.Entry<String,Object> entry :entrySet){
            cmd = cmd.replaceAll("\\$\\{"+entry.getKey()+"}", String.valueOf(entry.getValue()));
        }
        return cmd;
    }

}
