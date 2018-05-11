package com.example.framework.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * TimeUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat DATE_FORMAT_DATE_TIME =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_DATE_CHINESE =
            new SimpleDateFormat("yyyy年MM月dd日");

    public static final SimpleDateFormat DATA_FORMAT_MONTH_DATA_HOURS =
            new SimpleDateFormat("MM月dd日 HH:mm");

    /**
     * long time to string
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static Date strToDate(String dateStr) {
        Date date = null;
        try {
            if (!TextUtils.isEmpty(dateStr)) {
                date = DATE_FORMAT_DATE.parse(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化时间显示
     *
     * @param time 时间 (秒)
     * @return 字符串 格式为 HH:mm:ss
     */
    public static String formatTimeValue(int time) {
        time = time < 0 ? 0 : time;
        int i = time;
        // i /= 1000;//毫秒的转换
        int minute = i / 60;
        int hour = minute / 60;
        int second = i % 60;
        minute %= 60;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, minute, second);
    }

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * HH:mm    15:44
     * h:mm a    3:44 下午
     * HH:mm z    15:44 CST
     * HH:mm Z    15:44 +0800
     * HH:mm zzzz    15:44 中国标准时间
     * HH:mm:ss    15:44:40
     * yyyy-MM-dd    2016-08-12
     * yyyy-MM-dd HH:mm    2016-08-12 15:44
     * yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     * yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     * EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     * yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     * K:mm a    3:44 下午
     * EEE, MMM d, ''yy    周五, 八月 12, '16
     * hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     * yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     * EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     * yyMMddHHmmssZ    160812154440+0800
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     */
    public static String getTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    public static String getTime(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String longToStr(long l) {
        Date addTime = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        String time = format.format(addTime);
        return time;
    }

    public static String longToStr2(long l) {
        Date addTime = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String time = format.format(addTime);
        return time;
    }

    public static String longToStr3(long l) {
        Date addTime = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(addTime);
        return time;
    }


    public static String longToStr4(long milliseconds) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(milliseconds);
        return formater.format(date);
    }

    public static String getCurrentTime() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formater.format(date);
    }

    public static String getYear(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getMonth(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }

    /**
     * 毫秒转日期
     *
     * @param millisecond 毫秒数
     * @param pattern     正则式
     * @return 日期
     */
    public static String millisecondToDate(long millisecond, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(millisecond);
        return format.format(date);
    }

    /**
     * 获取年
     *
     * @param mills
     * @return
     */
    public static String getYearInMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


    /**
     * 获取月份
     *
     * @param mills
     * @return
     */
    public static String getMonthInMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取天数
     *
     * @param mills
     * @return
     */
    public static String getDayInMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取小时
     *
     * @param mills
     * @return
     */
    public static String getHourInMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }


    /**
     * 获取分钟
     *
     * @param mills
     * @return
     */
    public static String getMinuteInMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return String.valueOf(calendar.get(Calendar.MINUTE));
    }

    /**
     * 把long时间转换小时或分钟
     *
     * @param time 小时或分钟
     * @return
     */
    public static String getLessTime(long time) {
        if (time < 0) {
            return "";
        }
        int totalSeconds = (int) (time / 1000);
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.valueOf(hours + "小时" + minutes + "分钟")
                : String.valueOf(minutes + "分钟");
    }

    /**
     * 把long时间转换成时间格式字符串
     *
     * @param time 时间
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        String secondStr = "";
        String minuteStr = "";
        String hourStr = "";
        if (seconds < 10) {
            secondStr = "0" + seconds;
        } else {
            secondStr = "" + seconds;
        }
        if (minutes < 10) {
            minuteStr = "0" + minutes;
        } else {
            minuteStr = "" + minutes;
        }
        if (hours < 10) {
            hourStr = "0" + hours;
        } else {
            hourStr = "" + hours;
        }
        StringBuilder builder = new StringBuilder("");
        builder.append(hourStr).append(":").append(minuteStr).append(":").append(secondStr);
        return builder.toString();
    }


    public static String dealTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Long time1 = new Long(timestamp);
        String sd = sdf.format(new Date(time1));
        return sd;
    }

    public static int comareToDate(String myString) {
        int flag = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String nowdata = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date mydate = null;
        Date d2 = null;
        try {
            mydate = sdf.parse(myString);
            d2 = sdf.parse(nowdata);
            flag = d2.compareTo(mydate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
