package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 *
 */
public final class DateUtil {

    private static Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();

    /**
     * 不可实例化
     */
    private DateUtil(){}
    
    
    private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private static SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    /**
     * 一天的毫秒数
     *
     */
    private static long milliSecondsPerDay=24*60*60*1000;
    
    
    /**
     * sleep
     * @param millis 需要休眠的毫秒
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 获取世界末日（9999-12-31 23:59:59)
     * @return
     */
    public static Date getTheEndDate(){
        Calendar calendar=Calendar.getInstance();
        //特别注意：month是从0开始，12月对应11
        calendar.set(9999,11,31,23,59,59);
        return calendar.getTime();
    }

    /**
     * 将Date类型转换为制定格式的String类型
     * @param date
     * @param format
     * @return
     */
    public static String getFormatTime(Date date, String format){
        SimpleDateFormat formatter= getSimpleDateFormat(format);
    
        return formatter.format(date);
    }
    
    
    /**
     * 计算两个日期差 相隔几天
     * left - right
     * @param left
     * @param right
     * @return
     */
    public static long diffDay(Date left,Date right){
        Date leftDayBegin=getDayBegin(left);
        Date rightDayBegin=getDayBegin(right);
        return  (leftDayBegin.getTime()-rightDayBegin.getTime())/milliSecondsPerDay;
    }
    
    
    /**
     * 获取date当天0点的时间
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date){
       String dayBeginStr= simpleFormat.format(date);
        try {
            return simpleFormat.parse(dayBeginStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 缓存中获取SimpleDateFormat
     * @param format
     * @return
     */
    private static SimpleDateFormat getSimpleDateFormat(String format){
        SimpleDateFormat formatter = dateFormatMap.get(format);
        if (null==formatter) {
            formatter = new SimpleDateFormat(format);
            System.out.println("===============");
            dateFormatMap.put(format, formatter);
        }
        return formatter;
    }
    
    
    /**
     * 解析时间
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseDate(String dateStr,String format){
        SimpleDateFormat formatter=getSimpleDateFormat(format);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 增加天数
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date,int days){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        
        return calendar.getTime();
    }

    /**
     * 减少天数
     * @param date
     * @param days
     * @return
     */
    public static Date decreaseDays(Date date,int days){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) - days);

        return calendar.getTime();
    }
    
    /**
     * 增加秒数
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date,int seconds){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND,seconds);
        
        return calendar.getTime();
    }
    
    
    /**
     * 增加月数
     * @param date
     * @param moths
     * @return
     */
    public static Date addMoths(Date date,int moths){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,moths);
        
        return calendar.getTime();
    }

    /**
     * 根据时间戳和时间就策略获取对应的时间String信息
     *
     * 1502294400000L d -> 20170810
     * 1502294400000L m -> 201708
     * 1502294400000L y -> 2017
     *
     * @param timestamp             时间戳
     * @param prodtransTimeStrategy 时间策略
     * @return 对应的时间String
     */
    public static String getFormatDateStr(long timestamp, String prodtransTimeStrategy) {

        Date date = new Date(timestamp);
        switch (prodtransTimeStrategy) {
            case "d":
                return getSimpleDateFormat("yyyyMMdd").format(date);
            case "m":
                return getSimpleDateFormat("yyyyMM").format(date);
            case "y":
                return getSimpleDateFormat("yyyy").format(date);
            case "l":
                return null;
            default:
                return null;
        }
    }

    /**
     * 获取当前时间的开始时间
     * 按日查询情况下，开始时间是对应日期的0点0分0秒0毫秒取时间戳
     * 按月查询情况下，开始时间是对应月份1号的0点0分0秒0毫秒取时间戳
     *
     * @param dateStr               时间(20170809,201708,2017)
     * @param prodtransTimeStrategy 时间策略（d,m,y）
     * @return 当前时间的开始时间
     */
    public static Long getDateBegin(String dateStr, String prodtransTimeStrategy) {

        SimpleDateFormat sdf = null;
        Date date = null;
        try {
            switch (prodtransTimeStrategy) {
                case "d":
                    sdf = new SimpleDateFormat("yyyyMMdd");
                    date = sdf.parse(dateStr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    return calendar.getTimeInMillis();
                case "m":
                    sdf = new SimpleDateFormat("yyyyMM");
                    date = sdf.parse(dateStr);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    return calendar.getTimeInMillis();
                case "y":
                    sdf = new SimpleDateFormat("yyyy");
                    date = sdf.parse(dateStr);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.DAY_OF_YEAR, 1);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    return calendar.getTimeInMillis();
                case "l":
                    return null;
                default:
                    return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前时间的结束时间
     * 按日查询情况下，开始时间是对应日期的23点59分59秒999毫秒取时间戳
     * 按月查询情况下，开始时间是对应月份最后一天的23点59分59秒999毫秒取时间戳
     *
     * @param dateStr               时间(20170809,201708,2017)
     * @param prodtransTimeStrategy 时间策略（d,m,y）
     * @return 当前时间的结束时间
     */
    public static Long getDateEnd(String dateStr, String prodtransTimeStrategy) {

        SimpleDateFormat sdf = null;
        Date date = null;
        try {
            switch (prodtransTimeStrategy) {
                case "d":
                    sdf = new SimpleDateFormat("yyyyMMdd");
                    date = sdf.parse(dateStr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.roll(Calendar.HOUR_OF_DAY, -1);
                    calendar.roll(Calendar.MINUTE, -1);
                    calendar.roll(Calendar.SECOND, -1);
                    calendar.set(Calendar.MILLISECOND, 999);
                    return calendar.getTimeInMillis();
                case "m":
                    sdf = new SimpleDateFormat("yyyyMM");
                    date = sdf.parse(dateStr);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.roll(Calendar.DAY_OF_MONTH, -1);
                    calendar.roll(Calendar.HOUR_OF_DAY, -1);
                    calendar.roll(Calendar.MINUTE, -1);
                    calendar.roll(Calendar.SECOND, -1);
                    calendar.set(Calendar.MILLISECOND, 999);
                    return calendar.getTimeInMillis();
                case "y":
                    sdf = new SimpleDateFormat("yyyy");
                    date = sdf.parse(dateStr);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.roll(Calendar.DAY_OF_YEAR, -1);
                    calendar.roll(Calendar.HOUR_OF_DAY, -1);
                    calendar.roll(Calendar.MINUTE, -1);
                    calendar.roll(Calendar.SECOND, -1);
                    calendar.set(Calendar.MILLISECOND, 999);
                    return calendar.getTimeInMillis();
                case "l":
                    return null;
                default:
                    return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * 判断两个日期是否是同一天
     * @param leftDate
     * @param rightDate
     * @return
     */
    public static boolean isSameDay(Date leftDate,Date rightDate){
       long diff = diffDay(leftDate,rightDate);
       return 0 == diff;
    }
    
    
    /**
     * 把时间格式成yyyy-MM-dd HH:mm:ss.SSS字符串
     * @param date
     * @return
     */
    public static String longFormat(Date date){
        return longFormat.format(date);
    }
    
}
