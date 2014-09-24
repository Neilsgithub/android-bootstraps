package info.xiequan.androidbootstraps.util.common;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by spark on 25/8/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class DateUtil {
    public final static SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat DATETIME_WITH_MILLISECS_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
    public final static SimpleDateFormat DAY_FORMATTER = new SimpleDateFormat("MM月dd日");
    public final static SimpleDateFormat DAY_FORMATTER_SHORT = new SimpleDateFormat("M月d日");
    public final static SimpleDateFormat DATETIME_WITH_ZONE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public final static SimpleDateFormat TWITTER_FORMATTER = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);


    /**
     * Format date time to string "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime
     * @return date time string
     */
    public static String formatDateTime(Date dateTime) {
        return DATETIME_FORMATTER.format(dateTime);
    }

    /**
     * Format million seconds to string "yyyy-MM-dd HH:mm:ss".
     *
     * @param millionSeconds
     * @return date time string
     */
    public static String formatDateTime(long millionSeconds) {
        return formatDateTime(new Date(millionSeconds));
    }

    /**
     * Format date time to string "yyyy-MM-dd HH:mm:ss.SSS".
     *
     * @param dateTime
     * @return date time string
     */
    public static String formatDateTimeWithMilliSecs(Date dateTime) {
        return DATETIME_WITH_MILLISECS_FORMATTER.format(dateTime);
    }

    /**
     * Format date to string "yyyy-MM-dd".
     *
     * @param date
     * @return date string
     */
    public static String formatDate(Date date) {
        return DATE_FORMATTER.format(date);
    }

    public static String formatDay(Date date) {
        return DAY_FORMATTER.format(date);
    }

    public static String formatDaySimple(Date date) {
        return DAY_FORMATTER_SHORT.format(date);
    }

    /**
     * Format time to string "HH:mm:ss".
     *
     * @param dateTime
     * @return time string
     */
    public static String formatTime(Date dateTime) {
        return TIME_FORMATTER.format(dateTime);
    }

    /**
     * Format time to string "MMM dd yyyy".
     *
     * @param dateTime
     * @return time string
     */
    public static String formatTwitterDate(Date dateTime) {
        return TWITTER_FORMATTER.format(dateTime);
    }

    /**
     * Parse date string to Date "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTimeStr
     * @return Date
     */
    public static Date parseDateTime(String dateTimeStr) {
        try {
            return DATETIME_FORMATTER.parse(dateTimeStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse date string to Date "yyyy-MM-dd".
     *
     * @param dateStr
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        try {
            return DATE_FORMATTER.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse time string to Time "HH:mm:ss".
     *
     * @param timeStr
     * @return Date
     */
    public static Time parseTime(String timeStr) {
        try {
            return new Time(TIME_FORMATTER.parse(timeStr).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse date string to Date "yyyy-MM-ddTHH:mm:ssZ".
     *
     * @param dateTimeStr
     * @return Date
     */
    public static Date parseDateTimeWithZone(String dateTimeStr) {
        try {
            return DATETIME_WITH_ZONE_FORMATTER.parse(dateTimeStr);
        } catch (Exception e) {
            return null;
        }
    }


    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if(time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if(curDate.equals(paramDate)){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
            return ftime;
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days == 0){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
        }
        else if(days == 1){
            ftime = "昨天";
        }
        else if(days == 2){
            ftime = "前天";
        }
        else if(days > 2 && days <= 10){
            ftime = days+"天前";
        }
        else if(days > 10){
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate){
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if(time != null){
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if(nowDate.equals(timeDate)){
                b = true;
            }
        }
        return b;
    }

}
