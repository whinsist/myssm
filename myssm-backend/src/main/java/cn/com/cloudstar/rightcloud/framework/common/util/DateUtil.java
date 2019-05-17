/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Period;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author xiaolu
 * @date 2017/5/16
 */
public class DateUtil {

    private static long ND = 1000 * 24 * 60 * 60;
    private static long NH = 1000 * 60 * 60;
    private static String UTC_FORMAT_WITH_SECOND = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static String UTC_FORMAT_WITHOUT_SECOND = "yyyy-MM-dd'T'HH:mm'Z'";
    private static String UTC_PATTERN_WITHOUT_SECOND = "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}Z$";

    /**
     * Date 转化为LocalDate
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * UNIX时间戳转换成指定格式日期字符串
     *
     * @param timeStampString 时间戳 如："1473048265";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String timeStamp2Date(String timeStampString, String formats) {
        Long timestamp = Long.parseLong(timeStampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 日期格式字符串转换成UNIX时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * LocalDate 转化为Date
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }


    /**
     * 获取两个日期相差的小时数
     *
     * @param now  当前日期
     * @param date 数据的创建日期
     * @return 小时差
     */
    public static Long getHourDiff(Date now, Date date) {

        long diff = now.getTime() - date.getTime();
        long day = diff / ND;
        // 计算差多少小时
        long hour = diff % ND / NH + day * 24;
        return hour;
    }

    /**
     * 获取两时间间隔小时数
     *
     * @param roundingMode 舍入模式BigDecimal.*
     */
    public static Long getHourDiffHalfUp(Date now, Date date, int roundingMode) {
        long seconds = Math.abs(now.getTime() - date.getTime()) / 1000;
        BigDecimal minutes = new BigDecimal(seconds).divide(new BigDecimal(60), 0, roundingMode);
        BigDecimal hours = minutes.divide(new BigDecimal(60), 0, roundingMode);

        return hours.longValue();
    }

    /**
     * 获取两个日期相差的分钟数
     *
     * @param now  当前日期
     * @param date 数据的创建日期
     * @return 分钟差
     */
    public static Long getSecondDiff(Date now, Date date) {
        long diff = now.getTime() / (1000) - date.getTime() / (1000);
        return diff;
    }

    /**
     * 获取两个时间相差的天数
     *
     * @param now  当前日期
     * @param date 数据的创建日期
     * @return 天数差
     */
    public static Long getDayDiff(Date now, Date date) {
        long diff = now.getTime() - date.getTime();
        long day = diff / ND;
        return day;
    }

    /**
     * 根据创建时间和现在的时间获取数组
     *
     * @param hour  创建时间
     * @param now   现在的时间
     * @param array 数组
     * @return 数组
     */
    public static int[] getArrayOfHour(Date hour, Date now, int[] array, int num) {
        int index = getHourIndex(hour, now);
        for (int i = index; i < array.length; i++) {
            array[i] += num;
        }
        return array;
    }

    /**
     * 获得插入数据的位置
     */
    public static int getHourIndex(Date hour, Date now) {
        Date[] hourNum = getHourNum(now);
        int index = 0;
        for (int i = 0; i < hourNum.length; i++) {
            if (hour.getTime() < hourNum[0].getTime()) {
                index = 0;
                break;
            }
            if (hour.getTime() > hourNum[i].getTime() && hour.getTime() < hourNum[i + 1].getTime()) {
                index = i + 1;
                break;
            }
        }
        return index;
    }

    /**
     * 小时数组
     *
     * @return 小时数组
     */
    public static Date[] getHourNum(Date date) {
        Date[] xAxis = new Date[7];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 6; i >= 0; i--) {
            xAxis[i] = calendar.getTime();
            if (i == 1) {
                calendar.add(Calendar.HOUR_OF_DAY, -3);
            } else {
                calendar.add(Calendar.HOUR_OF_DAY, -4);
            }
        }
        return xAxis;
    }

    /**
     * 根据创建时间和现在的时间获取数组
     *
     * @param day    创建时间
     * @param nowDay 现在的时间
     * @param array  数组
     * @return 数组
     */
    public static int[] getArrayOfWeek(Date day, Date nowDay, int[] array, int num) {
        int index = getWeekIndex(day, nowDay);
        for (int i = index; i < array.length; i++) {
            array[i] += num;
        }
        return array;
    }

    /**
     * 获得插入数据的位置
     */
    public static int getWeekIndex(Date day, Date nowDay) {
        Date[] week = getWeekNum(nowDay);
        int index = 0;
        for (int i = 0; i < week.length; i++) {
            if (day.getTime() > week[i].getTime() && day.getTime() < week[i + 1].getTime()) {
                index = i + 1;
                break;
            } else if (day.getTime() <= week[0].getTime()) {
                index = 0;
            }
        }
        return index;
    }

    /**
     * 星期数组
     *
     * @return X轴的星期数
     */
    public static Date[] getWeekNum(Date nowDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDay);
        Date[] xAxis = new Date[7];
        for (int i = xAxis.length - 1; i >= 0; i--) {
            xAxis[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        return xAxis;
    }

    /**
     * 获取总览界面计算部分的X轴坐标数组，以小时区分
     *
     * @return X轴的坐标数组
     */
    public static Date[] getXAxisOfHour(Date hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hour);
        Date[] hourNum = getHourNum(hour);
        return hourNum;
    }

    public static Date[] getHourNumExclude(Date date) {
        Date[] xAxis = new Date[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = sdf.format(date);
        Date newDate = null;
        try {
            newDate = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        for (int i = 6; i >= 0; i--) {
            xAxis[i] = calendar.getTime();
            if (i == 1) {
                calendar.add(Calendar.HOUR_OF_DAY, -3);
            } else {
                calendar.add(Calendar.HOUR_OF_DAY, -4);
            }
        }
        return xAxis;
    }

    /**
     * 星期数组
     *
     * @return X轴的星期数
     */
    public static String[] getWeek(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] weekString = new String[7];
        for (int i = weekString.length - 1; i >= 0; i--) {
            weekString[i] = format.format(calendar.getTime());
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        }
        return weekString;
    }

    /**
     * 获得提示框内容
     */
    public static Map[] getTipsArrayOfHour(Object key, Date time, int size, Map[] maps, Date nowHour) {
        int index = getHourIndex(time, nowHour);
        for (int i = index; i < maps.length; i++) {
            if (maps[i].containsKey(key)) {
                maps[i].put(key, ((HashMap<String, Integer>) maps[i]).get(key) + size);
            } else {
                maps[i].put(key, size);
            }
        }
        return maps;
    }

    /**
     * 获得提示框内容
     */
    public static Map[] getTipsArrayOfWeek(Object key, Date day, int size, Map[] maps, Date nowDay) {
        int index = getWeekIndex(day, nowDay);
        for (int i = index; i < maps.length; i++) {
            if (maps[i].containsKey(key)) {
                maps[i].put(key, ((HashMap<String, Integer>) maps[i]).get(key) + size);
            } else {
                maps[i].put(key, size);
            }
        }
        return maps;
    }

    /**
     * 将UTC格式的时间转化为Date格式
     */
    public static Date utcToDate(String utc) {
        Pattern utcPatternWithoutSecond = Pattern.compile(UTC_PATTERN_WITHOUT_SECOND);
        try {
            // 裁剪一下
            if(utc.indexOf(".")>-1){
                String remove = utc.substring(utc.indexOf("."),utc.indexOf("Z"));
                utc = utc.replace(remove,"");
            }
            SimpleDateFormat sdf = null;
            if (utcPatternWithoutSecond.matcher(utc).matches()) {
                sdf = new SimpleDateFormat(UTC_FORMAT_WITHOUT_SECOND);
            } else {
                sdf = new SimpleDateFormat(UTC_FORMAT_WITH_SECOND);
            }
            Date date = sdf.parse(utc);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date formatDate(String date) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date parseDate(String date, String pattern) {
        try {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(long date, String pattern) {
        try {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.format(new Date(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取上一个时间段
     */
    public static Date getBeforeData(Date date, boolean isDay) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        if (isDay) {
            currentDate.add(Calendar.HOUR_OF_DAY, -4);
        } else {
            currentDate.add(Calendar.DAY_OF_MONTH, -1);
        }

        Date nextDate = currentDate.getTime();
        return nextDate;
    }

    /**
     * 当天的开始时间
     * @return
     */
    public static String startOfToDay(boolean returnTimestamp) {
        Calendar currentDate = Calendar.getInstance();
        //日期回滚1天
        currentDate.add(Calendar.DAY_OF_MONTH, -1);

        Date date = currentDate.getTime();
        if (returnTimestamp) {
            return String.valueOf(date.getTime()/1000);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(date.getTime());
            return Timestamp.valueOf(timeStr).toString();
        }
    }

    public static String startOfToDayFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar currentDate = Calendar.getInstance();
        //日期回滚1天
        currentDate.add(Calendar.DAY_OF_MONTH, -1);
        Date date = currentDate.getTime();
        return df.format(date);
    }

    /**
     * 当天的开始时间
     * @return
     */
    public static Date getNextDate(Date date) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.add(Calendar.DAY_OF_MONTH, 1);
        return currentDate.getTime();
    }

    /**
     * 当天的结束时间
     * @return
     */
    public static String endOfTodDay(boolean returnTimestamp) {
        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        if (returnTimestamp) {
            return String.valueOf(date.getTime()/1000);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(date.getTime());
            return Timestamp.valueOf(timeStr).toString();
        }
    }

    public static String endOfTodDayFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        return df.format(date);
    }

    /**
     * 功能：获取本周的开始时间 示例：2013-05-13 00:00:00
     * @param returnTimestamp 当前开始时间
     */
    public static String startOfThisWeek(boolean returnTimestamp) {
        Calendar currentDate = Calendar.getInstance();
        //日期回滚7天
        currentDate.add(Calendar.DAY_OF_MONTH, -7);
        Date date = currentDate.getTime();
        if (returnTimestamp) {
            return String.valueOf(date.getTime()/1000);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(date.getTime());
            return Timestamp.valueOf(timeStr).toString();
        }
    }

    public static String startOfThisWeekFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar currentDate = Calendar.getInstance();
        //日期回滚7天
        currentDate.add(Calendar.DAY_OF_MONTH, -7);
        Date date = currentDate.getTime();
        return df.format(date);
    }

    /**
     * 功能：获取本周的结束时间 示例：2013-05-19 23:59:59
     */
    public static String endOfThisWeek(boolean returnTimestamp) {// 当周结束时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date date = currentDate.getTime();
        if (returnTimestamp) {
            return String.valueOf(date.getTime()/1000);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(date.getTime());
            return Timestamp.valueOf(timeStr).toString();
        }
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(date.getTime());
        return Timestamp.valueOf(timeStr).toString();
    }

    public static String dateFormat(Date date,String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String timeStr = df.format(date.getTime());
        return timeStr;
    }

    public static Date timestap2Date(long timestap) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.valueOf(timestap);
        String d = format.format(time);
        Date date;
        try {
            date = format.parse(d);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Period> getPeriod(LocalDate start, LocalDate end, Period.PeriodType periodType) {
        List<Period> periods = new ArrayList<>();
        Period thisPeriod = new Period(end, periodType);
        //月结束时间以传入为准
        if (Period.PeriodType.Monthly.equals(periodType)) {
            thisPeriod.setPeriodEnd(end);
        }
        //开始于结束同属一月则period的开始结束以传入为准
        if (start.getMonthValue() == end.getMonthValue() && Period.PeriodType.Monthly.equals(periodType)) {
            thisPeriod.setPeriodStart(start);
        }
        periods.add(thisPeriod);
        if (!start.isEqual(end)) {
            for (int i = 1; ; i++) {
                if (start.getMonthValue() == end.getMonthValue() && Period.PeriodType.Monthly.equals(periodType)) {
                    break;
                }
                Period curr = new Period(periods.get(i - 1).getPeriodStart().minusDays(1), periodType);
                LocalDate minStart = curr.getPeriodStart();
                if (minStart.isBefore(start) || minStart.isEqual(start)) {
                    long exitsCount = periods.stream().filter(per -> per.getPeriodStart().isEqual(start)).count();
                    if (exitsCount == 0) {
                        curr.setPeriodStart(start);
                        periods.add(curr);
                    }
                    break;
                }
                periods.add(curr);
            }
        } else {
            thisPeriod.setPeriodStart(start);
            thisPeriod.setPeriodEnd(end);
        }
        periods.sort(Comparator.comparing(Period::getPeriodStart));
        return periods;
    }

    public static List<Period> getPeriod(LocalDate now, Period.PeriodType periodType, int periodNum) {
        return getPeriod(now, periodType, periodNum, true);
    }

    public static List<Period> getPeriod(LocalDate now, Period.PeriodType periodType, int periodNum, boolean ascOrder) {
        List<Period> periods = new ArrayList<>(periodNum);
        Period thisPeriod = new Period(now, periodType);
        periods.add(thisPeriod);
        for (int i = 1; i < periodNum; i++) {
            periods.add(new Period(periods.get(i - 1).getPeriodStart().minusDays(1), periodType));
        }

        if (ascOrder) {
            periods.sort(Comparator.comparing(Period::getPeriodStart));
        }

        return periods;
    }

    /**
     * 获取开始时间与当前时间的时差
     */
    public static String parseDateDiff(Date startTime) {
        if (Objects.isNull(startTime)) {
            return "";
        }
        Long minute = 1000 * 60L;
        Long hour = 1000 * 60 * 60L;
        Long day = 1000 * 60 * 60 * 24L;
        Long year = 1000 * 60 * 60 * 24 * 365L;


        Date now = Calendar.getInstance().getTime();
        Float diff = Float.valueOf(now.getTime() - startTime.getTime());
        if (diff < minute) {
            return "刚刚";
        } else if (minute <= diff && diff < hour) {
            return Math.round(diff / minute) + "分钟";
        } else if (hour <= diff && diff < day) {
            return Math.round(diff / hour) + "小时";
        } else if (day <= diff && diff < year) {
            return Math.round(diff / day) + "天";
        } else if (diff >= year) {
            return Math.floor(diff / year) + "年以上";
        }
        return "";
    }

    public static String licenseExpireDay(long diffTime) {
        if (diffTime < 0) {
            return "已经过期";
        }
        long nd = 1000 * 24 * 60 * 60;
        long day = diffTime / nd;
        if (day > 0) {
            return "还剩" + day + "天";
        } else {
            return "还剩" + "不到一天";
        }
    }


}
