package com.qmm.integrate.util;

import com.qmm.integrate.exception.CustomError;
import com.qmm.integrate.exception.CustomException;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * @author qinmengmei
 */
public class DateUtils {
    private static final String DATE_STRING_NOT_NULL = "The 'dateString' must not be null!";
    private static final String DATE_NOT_NULL = "The 'date' must not be null!";

    private DateUtils() {

    }

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 默认时间格式：yyyyMM
     */
    public static final String YYYYMM_DATE_PATTERN = "yyyyMM";

    /**
     * 默认时间格式：yyyyMMdd
     */
    public static final String YYYYMMDD_DATE_PATTERN = "yyyyMMdd";

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间戳格式，到毫秒 yyyy-MM-dd HH:mm:ss SSS
     */
    public static final String DEFAULT_DATEDETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 1小时折算成毫秒数
     */
    public static final long MILLIS_A_HOUR = 24 * 60 * 1000L;

    /**
     * 1天折算成毫秒数
     */
    public static final long MILLIS_A_DAY = 24 * 3600 * 1000L;

    /**
     * 取得系统当前年份
     *
     * @return
     */
    public static int currentYear() {
        return DateTime.now().getYear();
    }

    /**
     * 取得当前系统日期
     *
     * @return
     */
    public static Date currentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 取得系统当前日期，返回默认日期格式的字符串。
     *
     * @param strFormat
     * @return
     */
    public static String nowDate(String strFormat) {
        Assert.notNull(strFormat, DATE_STRING_NOT_NULL);
        return new DateTime().toString(strFormat, Locale.CHINESE);
    }

    /**
     * 取得当前系统时间戳
     *
     * @return
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(new DateTime().getMillis());
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString
     * @param pattern 日期格式
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString, String pattern) {
        Assert.notNull(dateString, DATE_STRING_NOT_NULL);
        Assert.notNull(pattern, "The 'pattern' must not be null!");
        return DateTime.parse(dateString, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString example:"2017-05-03T15:11:45.7009265+08:00"
     * @return
     * @throws Exception
     */
    public static Date toISODate(String dateString) {
        Assert.notNull(dateString, DATE_STRING_NOT_NULL);
        return DateTime.parse(dateString, ISODateTimeFormat.dateTime()).toDate();
    }

    /**
     * 将日期字符串转换为java.util.Date对象，使用默认日期格式
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString) {
        Assert.notNull(dateString, DATE_STRING_NOT_NULL);
        return LocalDateTime.parse(dateString).toDate();
    }

    /**
     * 将时间字符串转换为java.util.Date对象
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDateTime(String dateString) {
        Assert.notNull(dateString, DATE_STRING_NOT_NULL);
        return DateTime.parse(dateString, DateTimeFormat.forPattern(DEFAULT_DATETIME_PATTERN)).toDate();
    }

    /**
     * 将java.util.Date对象转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toDateString(Date date, String pattern) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(pattern, "The 'pattern' must not be null!");
        return new DateTime(date).toString(pattern, Locale.CHINESE);
    }

    /**
     * 将java.util.Date对象转换为字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        Assert.notNull(date, DATE_NOT_NULL);
        return new DateTime(date).toString(DEFAULT_DATE_PATTERN, Locale.CHINESE);
    }

    /**
     * 将java.util.Date对象转换为时间字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateTimeString(Date date) {
        Assert.notNull(date, DATE_NOT_NULL);
        return new DateTime(date).toString(DEFAULT_DATETIME_PATTERN, Locale.CHINESE);
    }

    /**
     * 日期相减
     *
     * @param date
     * @param days
     * @return
     */
    public static Date diffDate(Date date, Integer days) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(days, "The 'days' must not be null!");
        return new DateTime(date).minusDays(days).toDate();

    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     * @author doumingjun create 2007-04-07
     */
    public static long getMillis(Date date) {
        Assert.notNull(date, DATE_NOT_NULL);
        return new DateTime(date).getMillis();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param days 天数
     * @return 返回相加后的日期
     * @author doumingjun create 2007-04-07
     */
    public static Date addDate(Date date, Integer days) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(days, "The 'days' must not be null!");
        return new DateTime(date).plusDays(days).toDate();
    }

    /**
     * 日期增加年数
     *
     * @param date
     * @param years
     * @return
     * @author zhf
     */
    public static Date addYear(Date date, Integer years) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(years, DATE_STRING_NOT_NULL);
        return new DateTime(date).plusYears(years).toDate();
    }

    /**
     * 日期增加月数
     *
     * @param date
     * @param months
     * @return
     * @author zhf
     */
    public static Date addMonth(Date date, Integer months) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(months, DATE_STRING_NOT_NULL);
        return new DateTime(date).plusMonths(months).toDate();
    }

    /**
     * 日期增加小时
     *
     * @param date
     * @param hours
     * @return
     * @author zhf
     */
    public static Date addHours(Date date, Integer hours) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(hours, "The 'hours' must not be null!");
        return new DateTime(date).plusHours(hours).toDate();
    }

    /**
     * 日期增加分钟
     *
     * @param date
     * @param minutes
     * @return
     * @author zhf
     */
    public static Date addMinutes(Date date, Integer minutes) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(minutes, "The 'minutes' must not be null!");
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    /**
     * 日期增加秒
     *
     * @param date
     * @param seconds
     * @return
     * @author zhf
     */
    public static Date addSeconds(Date date, Integer seconds) {
        Assert.notNull(date, DATE_NOT_NULL);
        Assert.notNull(seconds, "The 'seconds' must not be null!");
        return new DateTime(date).plusSeconds(seconds).toDate();
    }

    private static final int TEN = 10;
    /**
     * 根据季度获得相应的月份
     *
     * @param quarters 季度
     *
     * @return 返回相应的月份
     */
    public static String getMonth(String quarters) {
        Assert.notNull(quarters, "The 'quarters' must not be null!");
        String month;
        int m = Integer.parseInt(quarters);
        m = m * 3 - 2;
        if (m > 0 && m < TEN) {
            month = "0" + m;
        } else {
            month = String.valueOf(m);
        }
        return month;
    }

    /**
     * 根据月份获得相应的季度
     *
     * @param month 月份
     *
     * @return 返回相应的季度
     */
    public static String getQuarters(String month) {
        Assert.notNull(month, "The 'month' must not be null!");
        String quarters = null;
        int m = Integer.parseInt(month);
        if (m == JANUARY || m == FEBRUARY || m == MARCH) {
            quarters = "1";
        }
        if (m == APRIL || m == MAY || m == JUNE) {
            quarters = "2";
        }
        if (m == JULY || m == AUGUST || m == SEPTEMBER) {
            quarters = "3";
        }
        if (m == OCTOBER || m == NOVEMBER || m == DECEMBER) {
            quarters = "4";
        }
        return quarters;
    }

    /**
     * 获取日期所在星期的第一天，这里设置第一天为星期日
     *
     * @param datestr
     * @return
     */
    public static String getFirstDateOfWeek(String datestr) {
        Assert.notNull(datestr, DATE_STRING_NOT_NULL);
        DateTime dt = DateTime.parse(datestr);
        return dt.plusDays(-(dt.getDayOfWeek()) + 1).toString(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取日期所在当年的第几周
     *
     * @param datestr
     * @return
     */
    public static int getWeekOfYear(String datestr) {
        Assert.notNull(datestr, DATE_STRING_NOT_NULL);
        return DateTime.parse(datestr).weekOfWeekyear().get();
    }

    /**
     * 通过日期字符串yyyy-MM-dd HH:mm:ss 获取星期
     *
     * @param datestr
     * @return
     */
    public static String getWeekday(String datestr) {
        Assert.notNull(datestr, DATE_STRING_NOT_NULL);
        try {
            switch (DateTime.parse(datestr).dayOfWeek().get()) {
                case 1:
                    return "星期一";
                case 2:
                    return "星期二";
                case 3:
                    return "星期三";
                case 4:
                    return "星期四";
                case 5:
                    return "星期五";
                case 6:
                    return "星期六";
                default:
                    return "星期天";
            }
        } catch (Exception ex) {
            throw new CustomException(CustomError.ERROR_DAY.getCode(), ex.getMessage(), ex);
        }

    }

    public static Date getDate(Object object) {
        Assert.notNull(object, "The 'object' must not be null!");
        if (object instanceof String) {
            return DateTime.parse((String) object).toDate();
        } else if (object instanceof Date || object instanceof Timestamp) {
            return (Date) object;
        } else if (object instanceof Long) {
            return new DateTime((Long) object).toDate();
        } else {
            throw new CustomException(CustomError.ERROR_DAY.getCode(), "this object can't to date!");
        }
    }

    public static Date fromTimeticks(Long ticks) {
        Assert.notNull(ticks, "The 'ticks' must not be null!");
        return new DateTime(ticks).toDate();
    }

    public static Long toTimeticks(Date time) {
        return time.getTime();
    }

    /**
     * 获取UTC时间戳（以秒为单位）
     */
    public static int getTimestampInSeconds() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 获取UTC时间戳（以毫秒为单位）
     */
    public static long getTimestampInMillis() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return cal.getTimeInMillis();
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static long getTomorrowZeroSeconds() {
        // 当前时间毫秒数
        long current = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowzero = calendar.getTimeInMillis();
        long tomorrowzeroSeconds = (tomorrowzero - current) / 1000;
        return tomorrowzeroSeconds;
    }

    public static void main(String[] args) throws ParseException {
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = Calendar.getInstance().get(Calendar.MINUTE);
        System.out.println(h);
        System.out.println(m);
    }
}

