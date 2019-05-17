/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.CriteriaResult;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

/**
 * Web层相关的实用工具类
 *
 * @author
 */
public class WebUtil {

    /**
     * 下拉列表框显示值Key
     */
    public static final String COMBOX_TEXT_FIELD = "textFieldKey";

    /**
     * 下拉列表框实际值Key
     */
    public static final String COMBOX_VALUE_FIELD = "valueFieldKey";

    public static MessageSource messageSource;

    static {
        try {
            // 获取消息处理类
            messageSource = new ClassPathXmlApplicationContext("classpath:spring-message-bean.xml");
            //"classpath:/config/spring/spring-message-bean.xml"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 从HttpRequest中创建Map信息
     *
     * 考虑有数组情况,所以需要采用Map<String, Object>格式
     *
     * <p>
     * Map值只有两种类型：字符串或字符串数组
     * </p>
     *
     * @param request
     * @return
     */
    public static Map<String, Object> buildMapFromHttpRequest(final HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap();
        Enumeration<?> paramNames = request.getParameterNames();
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (StringUtils.equalsIgnoreCase(paramName, "pagenum") ||
                    StringUtils.equalsIgnoreCase(paramName, "pagesize")) {
                continue;
            }
            String[] values = request.getParameterValues(paramName);
            //去掉数组中空值信息
            String[] effectiveValues = getEffectiveValue(values);
            if (effectiveValues.length > 1) {
                paramsMap.put(paramName, effectiveValues);
            }else if(effectiveValues.length == 1){
                paramsMap.put(paramName, effectiveValues[0]);
            }
        }
        return paramsMap;
    }

    /**
     * 取页面有效值集合
     * @param @param values
     * @param @return
     * @return String[]
     * @throws
     */
    public static String[] getEffectiveValue(String[] values){
        String []rtns = new String[]{};
        for (int i = 0; i < values.length; i++) {
            if(StringUtils.isNotBlank(values[i])){
                rtns = ArrayUtils.add(rtns, values[i]);
            }
        }
        return rtns;
    }

    /**
     * 设置分页查询条件相关参数。
     *
     * @param request              HTTP请求对象(从前台获取以"qm."开头的查询条件)
     * @param criteria             查询条件
     * @param defaultOrderByClause 默认排序条件
     */
    public static void preparePageParams(HttpServletRequest request, Criteria criteria, String defaultOrderByClause) {

        if (request == null || criteria == null) {
            return;
        }


//        // 取得分页信息
        String strPageSum = request.getParameter("pageNum");
        String strPageSize = request.getParameter("pageSize");

        // 设置分页信息
        setPaginationParam(criteria, strPageSum, strPageSize);



        // 取得排序信息
        String sortDatafield = request.getParameter("sortdatafield");
        String sortOrder = request.getParameter("sortorder");

        // 排序信息
        setOrderParams(criteria, defaultOrderByClause, sortDatafield, sortOrder);

        // 将请求参数Map
        HashMap<String, String> paramMap = getParameterMap(request);

        // 将查询条件设置到criteria中
        Iterator<Entry<String, String>> keyIterator = paramMap.entrySet().iterator();
        setRequestParams(criteria, keyIterator);
    }

    private static void setPagentionParam(Criteria criteria, String strPageSum, String strPageSize) {
        if (!StringUtil.isNullOrEmpty(strPageSum) && !StringUtil.isNullOrEmpty(strPageSize)) {
            int pageSize = Integer.valueOf(strPageSize);
            int recordStart = Integer.valueOf(strPageSum) * pageSize;
//            criteria.setMysqlLength(pageSize);
//            criteria.setMysqlOffset(recordStart);
        }
    }
    private static void setPaginationParam(Criteria criteria, String strPageSum, String strPageSize) {
        if (!StringUtil.isNullOrEmpty(strPageSum) && !StringUtil.isNullOrEmpty(strPageSize)) {
            int pageSize = Integer.valueOf(strPageSize);
            int pageNum = Integer.valueOf(strPageSum);
            // 当前的前端是页码开始是0，这里插件是从1开始的页面，所以默认+1
//            criteria.setPageNum(pageNum + 1);
            criteria.setPageNum(pageNum);
            criteria.setPageSize(pageSize);
        }
    }

    private static void setOrderParams(Criteria criteria, String defaultOrderByClause, String sortDatafield,
                                       String sortOrder) {
        if (StringUtils.isNotBlank(sortDatafield) && StringUtils.isNotBlank(sortOrder)) {
            String order = WebUtil.toClumn(sortDatafield) + " " + sortOrder;
            if (!Strings.isNullOrEmpty(defaultOrderByClause)) {
                order += ", " + defaultOrderByClause;
            }
            criteria.setOrderByClause(order);
        } else {
            // 默认以帐号排序
            criteria.setOrderByClause(defaultOrderByClause);
        }
    }

    /**
     * 将请求参数封装为Map<br>
     * request中的参数t1=1&t1=2&t2=3<br>
     * 形成的map结构：<br>
     * key=t1;value=1,2<br>
     * key=t2;value=3<br>
     *
     * @param request HTTP请求对象
     * @return 封装后的Map
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<String, String> getParameterMap(HttpServletRequest request) {
        // 返回值HaspMap
        HashMap<String, String> returnMap = new HashMap<String, String>();
        try {
            // 参数Map
            Map properties = request.getParameterMap();
            Iterator entries = properties.entrySet().iterator();
            Entry entry;
            String name = "";
            String value = "";
            while (entries.hasNext()) {
                entry = (Entry) entries.next();
                name = (String) entry.getKey();
                Object valueObj = entry.getValue();
                if (null == valueObj) {
                    value = "";
                } else if (valueObj instanceof String[]) {
                    String[] values = (String[]) valueObj;
                    for (String tempValue : values) {
                        value = tempValue + ",";
                    }
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = valueObj.toString();
                }
                returnMap.put(name, value);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnMap;
    }

    private static void setRequestParams(Criteria criteria, Iterator<Entry<String, String>> keyIterator) {
        while (keyIterator.hasNext()) {
            Entry<String, String> entry = keyIterator.next();
            String key = entry.getKey();
            if (key.indexOf("qm.") == 0 && StringUtils.isNotBlank(entry.getValue())) {
                criteria.put(key.substring(3), entry.getValue());
            }
        }
    }

    /**
     * 把pojo字段转为数据库字段<br>
     * fileName -> FILE_NAME
     *
     * @param field 变量名
     * @return 字段名
     */
    public static String toClumn(String field) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append("_").append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }


    /**
     * 将请求参数封装为Map<br>
     * request中的参数t1=1&t1=2&t2=3<br>
     * 形成的map结构：<br>
     * key=t1;value=1,2<br>
     * key=t2;value=3<br>
     *
     * @param request HTTP请求对象
     * @return 封装后的Map
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<String, String> getParameterMapZH(HttpServletRequest request) {
        // 返回值HaspMap
        HashMap<String, String> returnMap = new HashMap<String, String>();
        try {
            // 参数Map
            Map properties = request.getParameterMap();
            Iterator entries = properties.entrySet().iterator();
            Entry entry;
            String name = "";
            String value = "";
            while (entries.hasNext()) {
                entry = (Entry) entries.next();
                name = (String) entry.getKey();
                Object valueObj = entry.getValue();
                if (null == valueObj) {
                    value = "";
                } else if (valueObj instanceof String[]) {
                    String[] values = (String[]) valueObj;
                    for (String value1 : values) {

                        String tempValue = value1;
                        tempValue = new String(value1.getBytes("iso-8859-1"), "UTF-8");
                        value = tempValue + ",";
                    }
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = valueObj.toString();
                }
                returnMap.put(name, value);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * 直接输出字符串.
     */
    public static void renderText(HttpServletResponse response, String text) {

        render(response, text, "text/plain;charset=UTF-8");
    }

    /**
     * 使用Response输出指定格式内容.
     */
    protected static void render(HttpServletResponse response, String text, String contentType) {

        try {
            response.setContentType(contentType);
            if (StringUtil.isNullOrEmpty(text)) {
                text = "";
            }
            response.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接输出JSON.
     */
    public static void renderJson(HttpServletResponse response, String text) {

        render(response, text, "application/json; charset=utf-8");
    }

    /**
     * 直接输出HTML.
     */
    public static void renderHtml(HttpServletResponse response, String html) {

        render(response, html, "text/html;charset=UTF-8");
    }

    /**
     * 直接输出XML.
     */
    public static void renderXML(HttpServletResponse response, String xml) {

        render(response, xml, "text/xml;charset=UTF-8");
    }

    /**
     * MD5加密5
     *
     * @param data 数据值
     * @param salt 加密添加字符串
     * @return 加密后字符串
     */
    public static String encrypt(String data, String salt) {
        // 可以更换算法:sha512Hex
        return DigestUtils.md5Hex(data + "{" + salt.toLowerCase() + "}");
    }

    /**
     * MD5加密5
     *
     * @param data 数据值
     *             加密添加字符串
     * @return 加密后字符串
     */
    public static String encrypt(String data) {

        // 可以更换算法:sha512Hex
        return DigestUtils.md5Hex(data);
    }

    /**
     * BASE64加密
     */
    public static String encryptBase64(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    /**
     * 中文编码
     */
    public static String urlEncode(String name) {
        try {
            return URLEncoder.encode(name, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * BASE64解密
     */
    public static String decryptBase64(String data) {
        return new String(Base64.decodeBase64(data));
    }

    /**
     * 新增时添加用户以及当前时间信息
     */
    public static <T> void prepareInsertParams(T obj) {
        invokeSet(obj, "createdBy", RequestContextUtil.getCurrentUserName());
        invokeSet(obj, "updatedBy", RequestContextUtil.getCurrentUserName());
        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }


    /**
     * 新增用户是写入时间及操作人信息
     */
    public static <T> void prepareInsertParams(T obj, AuthUser authUserInfo) {
        if (authUserInfo != null && authUserInfo.getAccount() != null) {
            // 创建人
            invokeSet(obj, "createdBy", authUserInfo.getAccount());
            // 更新人
            invokeSet(obj, "updatedBy", authUserInfo.getAccount());
        } else {
            invokeSet(obj, "createdBy", "admin");
            invokeSet(obj, "updatedBy", "admin");
        }
        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 新增时添加用户以及当前时间信息(For Activiti)
     */
    public static <T> void prepareInsertParams(T obj, String user) {
        if (user != null && !StringUtil.EMPTY.equals(user)) {
            // 创建人
            invokeSet(obj, "createdBy", user);
            // 更新人
            invokeSet(obj, "updatedBy", user);
        }
        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 新增时添加管理员用户以及当前时间信息
     */
    public static <T> void prepareInsertAdminParams(T obj) {
        // 创建人
        invokeSet(obj, "createdBy", "admin");
        // 更新人
        invokeSet(obj, "updatedBy", "admin");

        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 更新时添加用户以及当前时间信息
     */
    public static <T> void prepareUpdateParams(T obj) {
        invokeSet(obj, "updatedBy", "admin");
        // 更新时间
        Date date = new Date();
        invokeSet(obj, "updatedDt", date);
    }

    /**
     * 更新时添加用户以及当前时间信息
     */
    public static <T> void prepareUpdateParams(T obj, String user) {
        if (user != null && !StringUtil.EMPTY.equals(user)) {
            // 更新人
            invokeSet(obj, "updatedBy", user);
        }
        // 更新时间
        Date date = new Date();
        invokeSet(obj, "updatedDt", date);
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    // TODO
    // private static Method getGetMethod(Class<? extends Object> objectClass,
    // String fieldName) {
    // StringBuffer sb = new StringBuffer();
    // sb.append("get");
    // sb.append(fieldName.substring(0, 1).toUpperCase());
    // sb.append(fieldName.substring(1));
    // try {
    // return objectClass.getMethod(sb.toString());
    // } catch (Exception e) {
    // }
    // return null;
    // }

    /**
     * java反射bean的set方法
     */
    @SuppressWarnings("rawtypes")
    private static Method getSetMethod(Class<? extends Object> objectClass, String fieldName) {

        try {
            Class[] parameterTypes = new Class[1];
            Field field = getField(objectClass, fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            return objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取类已经类的父的某一声明变量
     *
     * @param fieldName 变量名
     * @return Field 变量
     */
    @SuppressWarnings("rawtypes")
    private static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    /**
     * 执行set方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     * @param value     值
     */
    private static void invokeSet(Object o, String fieldName, Object value) {

        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行get方法
     *
     * @param o执行对象
     * @param fieldName属性
     */
    // TODO
    // private static Object invokeGet(Object o, String fieldName) {
    // Method method = getGetMethod(o.getClass(), fieldName);
    // try {
    // return method.invoke(o, new Object[0]);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    /**
     * 取得系统消息
     *
     * @param msgId 消息ID
     * @return 消息内容
     */
    public static String getMessage(String msgId) {
        return getMessage(msgId, null);
    }

    public static String getMessage(String msgId, Object[] arg) {
        return getMessage(msgId, arg, Locale.CHINA);
    }

    public static String getMessage(String msgId, Object[] args, Locale locale) {
        String message = StringUtils.EMPTY;
        try {
            message = messageSource.getMessage(msgId, args, locale);
        } catch (Exception e) {
        }
        return message;
    }

    /**
     * 取得前后N年分下拉列表Map
     *
     * @param yearCount   指定年数
     * @param addPreYear  是否取得前N年份(true:是;false:否)
     * @param addNextYear 是否取得后N年份(true:是;false:否)
     * @return 下拉列表Map
     */
    public static Map<String, String> getYearMap(int yearCount, boolean addPreYear, boolean addNextYear) {

        Map<String, String> yearMap = new LinkedHashMap<String, String>();

        // 日历取得
        Calendar cder = Calendar.getInstance();

        int cYear = cder.get(Calendar.YEAR);

        // 前N年
        if (addPreYear) {
            for (int i = yearCount; i >= 1; i--) {
                yearMap.put(String.valueOf(cYear - i), String.valueOf(cYear - i));
            }
        }
        // 今年
        yearMap.put(String.valueOf(cYear), String.valueOf(cYear));
        // 后N年
        if (addNextYear) {
            for (int j = 1; j <= yearCount; j++) {
                yearMap.put(String.valueOf(cYear + j), String.valueOf(cYear + j));
            }
        }

        return yearMap;
    }

    /**
     * 取得月份下拉列表Map
     *
     * @return 下拉列表Map
     */
    public static Map<String, String> getMonthMap() {

        Map<String, String> monthMap = new LinkedHashMap<String, String>();

        // 12月份
        monthMap.put("1", "01");
        monthMap.put("2", "02");
        monthMap.put("3", "03");
        monthMap.put("4", "04");
        monthMap.put("5", "05");
        monthMap.put("6", "06");
        monthMap.put("7", "07");
        monthMap.put("8", "08");
        monthMap.put("9", "09");
        monthMap.put("10", "10");
        monthMap.put("11", "11");
        monthMap.put("12", "12");

        return monthMap;
    }

    /**
     * 取得前后N年年份中文下拉列表Map
     *
     * @param yearCount   指定年数
     * @param addPreYear  是否取得前N年份(true:是;false:否)
     * @param addNextYear 是否取得后N年份(true:是;false:否)
     * @return 下拉列表Map
     */
    public static Map<String, String> getYearChineseMap(int yearCount, boolean addPreYear, boolean addNextYear) {

        Map<String, String> yearMap = new LinkedHashMap<String, String>();

        // 日历取得
        Calendar cder = Calendar.getInstance();

        int cYear = cder.get(Calendar.YEAR);

        // 前N年
        if (addPreYear) {
            for (int i = yearCount; i >= 1; i--) {
                yearMap.put(String.valueOf(cYear - i), String.valueOf(cYear - i) + "年");
            }
        }
        // 今年
        yearMap.put(String.valueOf(cYear), String.valueOf(cYear) + "年");
        // 后N年
        if (addNextYear) {
            for (int j = 1; j <= yearCount; j++) {
                yearMap.put(String.valueOf(cYear + j), String.valueOf(cYear + j) + "年");
            }
        }

        return yearMap;
    }

    public static Map<String, String> getMonthChineseMap() {
        Map<String, String> monthMap = new LinkedHashMap<String, String>();

        // 12月份
        monthMap.put("1", "1月");
        monthMap.put("2", "2月");
        monthMap.put("3", "3月");
        monthMap.put("4", "4月");
        monthMap.put("5", "5月");
        monthMap.put("6", "6月");
        monthMap.put("7", "7月");
        monthMap.put("8", "8月");
        monthMap.put("9", "9月");
        monthMap.put("10", "10月");
        monthMap.put("11", "11月");
        monthMap.put("12", "12月");

        return monthMap;
    }

    public static Map<String, String> getWeekMap() {
        Map<String, String> weekMap = new LinkedHashMap<String, String>();
        weekMap.put("0", "星期日");
        weekMap.put("1", "星期一");
        weekMap.put("2", "星期二");
        weekMap.put("3", "星期三");
        weekMap.put("4", "星期四");
        weekMap.put("5", "星期五");
        weekMap.put("6", "星期六");
        return weekMap;
    }

    public static Map<String, String> getQuarterMap() {
        Map<String, String> quarterMap = new LinkedHashMap<String, String>();
        quarterMap.put("1", "第一季度");
        quarterMap.put("2", "第二季度");
        quarterMap.put("3", "第三季度");
        quarterMap.put("4", "第四季度");
        return quarterMap;
    }

    /*
     * public static String getYearMapJson(int yearCount, boolean addPreYear,
     * boolean addNextYear) { ArrayList<Map<String, String>> yearList = new
     * ArrayList<Map<String, String>>(); // 日历取得 Calendar cder =
     * Calendar.getInstance(); int cYear = cder.get(Calendar.YEAR); Map<String,
     * String> yearMap = null; // 前N年 if (addPreYear) { for (int i = yearCount;
     * i >= 1; i--) { yearMap = new LinkedHashMap<String, String>();
     * yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear - i) + "年");
     * yearMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(cYear - i));
     * yearList.add(yearMap); } } // 今年 yearMap = new LinkedHashMap<String,
     * String>(); yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear) +
     * "年"); yearMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(cYear));
     * yearList.add(yearMap); // 后N年 if (addNextYear) { for (int j = 1; j <=
     * yearCount; j++) { yearMap = new LinkedHashMap<String, String>();
     * yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear + j) + "年");
     * yearMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(cYear + j));
     * yearList.add(yearMap); } } return JsonUtil.Encode(yearList); }
     *
     * public static String getMonthChineseMapJson() { ArrayList<Map<String,
     * String>> monthList = new ArrayList<Map<String, String>>();
     *
     * for (int i = 1; i <= 12; i++) { Map<String, String> monthMap = new
     * HashMap<String, String>(); monthMap.put(WebUtil.COMBOX_TEXT_FIELD,
     * String.valueOf(i) + "月"); monthMap.put(WebUtil.COMBOX_VALUE_FIELD,
     * String.valueOf(i));
     *
     * monthList.add(monthMap); }
     *
     * return JsonUtil.Encode(monthList); }
     *
     * public static String getWeekMapJson() { ArrayList<Map<String, String>>
     * weekList = new ArrayList<Map<String, String>>(); String[] num = new
     * String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; for (int i
     * = 0; i <= 6; i++) { Map<String, String> weekMap = new
     * LinkedHashMap<String, String>(); weekMap.put(WebUtil.COMBOX_TEXT_FIELD,
     * num[i]); weekMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(i + 1));
     * weekList.add(weekMap); } return JsonUtil.Encode(weekList); }
     *
     * public static String getQuarterMapJson() { ArrayList<Map<String, String>>
     * quarterList = new ArrayList<Map<String, String>>(); String[] num = new
     * String[] { "第一季度", "第二季度", "第三季度", "第四季度" }; for (int i = 0; i < 4; i++)
     * { Map<String, String> quarterMap = new LinkedHashMap<String, String>();
     * quarterMap.put(WebUtil.COMBOX_TEXT_FIELD, num[i]);
     * quarterMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(i + 1));
     * quarterList.add(quarterMap); } return JsonUtil.Encode(quarterList); }
     */

    /**
     * 取得客户端真实IP
     *
     * @return 客户端真实IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 根据当前系统时间生成ID
     *
     * @return 精确到毫秒
     */
    public static long IdGenerator() {

        long baseId;
        long t = System.currentTimeMillis();
        // 52~43
        baseId = t;
        baseId &= 0x1FF0000000L;
        baseId <<= 14;
        // 28~15
        t &= 0xFFFC000L;
        baseId |= t;
        // 42~29
        SecureRandom ng = new SecureRandom();
        t = ng.nextLong();
        t &= 0x3FFF0000000L;
        // 14~1
        baseId |= t;
        baseId |= t;
        baseId /= 10000;
        baseId *= 10000;
        baseId &= 0x1FFFFFFFFFFFFL;
        return baseId;

    }



    /**
     * 生成指定位数随机密码
     *
     * @return 随机密码
     */
    public static String randomPwd(int length) {

        String randomPwd = "";
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            randomPwd += buffer.charAt(r.nextInt(range));
        }
        return randomPwd;
    }

    /**
     * 小时转换成微秒
     */
    public static long convertTime(String hour) throws NullPointerException, NumberFormatException {
        double t = Double.parseDouble(hour);
        return (long) (t * 60 * 60 * 1000);
    }

    public static final void mergeObject(Object dest, Object orig) {
		/*BeanUtilsBean bean = BeanUtilsBean.getInstance();
		PropertyDescriptor origDescriptors[] = bean.getPropertyUtils().getPropertyDescriptors(orig);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if ("class".equals(name)) {
				continue;
			}
			if (bean.getPropertyUtils().isReadable(orig, name) && bean.getPropertyUtils().isWriteable(dest, name)) {
				try {
					Object value = bean.getPropertyUtils().getSimpleProperty(orig, name);
					if (isNotBlank(value)) {
						bean.copyProperty(dest, name, value);
					}
				} catch (NoSuchMethodException e) {
					; // Should not happen
				} catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}*/
    }

    public static final boolean isNotBlank(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() == String.class) {
            return StringUtils.isNotBlank((String) obj);
        } else {
            return true;
        }
    }

    /**
     * 生成指定位数随机帐号+日期
     *
     * @return 随机帐号+日期
     */
    public static String randomAccount(int length) {
        String randomAccount = "";
        StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            randomAccount += buffer.charAt(r.nextInt(range));
        }
        //使用日历类
        Calendar cal = Calendar.getInstance();
        //得到年
        String year = cal.get(Calendar.YEAR) + "";
        //得到月，因为从0开始的，所以要加1
        String month = (cal.get(Calendar.MONTH) + 1) + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        //得到天
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        if (day.length() == 1) {
            day = "0" + day;
        }
        String h = cal.get(Calendar.HOUR_OF_DAY) + "";
        if (h.length() == 1) {
            h = "0" + h;
        }
        String m = cal.get(Calendar.MINUTE) + "";
        if (m.length() == 1) {
            m = "0" + m;
        }
        String s = cal.get(Calendar.SECOND) + "";
        if (s.length() == 1) {
            s = "0" + s;
        }
        randomAccount =
                PropertiesUtil.getProperty("user.account.prefix") + year.substring(2, 4) + month + day + h + m + s;
        return randomAccount;
    }

    /**
     * 取得上传文件的文件名
     *
     * @param header request的header
     * @return 文件名
     */
    public static String getUploadFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        return StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 取得上传文件的文件名
     *
     * @param header request的header
     * @return 文件名
     */
    public static String makeUploadFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        String filename = Arrays.stream(contentDisposition).filter(s -> s.trim().startsWith("filename")).findFirst()
                .get().split("=")[1].trim().replaceAll("\"", "");
        String ext = filename.substring(filename.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "").concat(ext);
    }

    /**
     * 就近舍入
     * @param rate
     * @return
     */
    public static String parseRate(Double rate) {
        if (rate == null) {
            return "";
        }
        return Math.round(rate)+"";
    }

    /**
     * 把list分为增加的和删除的
     * @param hopeIdList
     * @param existIdList
     * @param <T>
     * @return
     */
    public static  <T> Map<String, List<T>> separatorInsertDeleteList(List<T> hopeIdList, List<T> existIdList) {
        Map<String, List<T>> result = new HashMap<>(2);
        if (CollectionUtil.isEmpty(existIdList)) {
            result.put("insertList", hopeIdList);
            return result;
        }
        List<T> insertList = new ArrayList<>();
        List<T> deleteList = new ArrayList<>();
        for (T id : hopeIdList) {
            if (!existIdList.contains(id)) {
                insertList.add(id);
            }
        }
        for (T existId : existIdList) {
            if (!hopeIdList.contains(existId)) {
                deleteList.add(existId);
            }
        }
        result.put("insertList", insertList);
        result.put("deleteList", deleteList);
        return result;
    }

    /**
     * 取得HttpRequest的简化函数.
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
