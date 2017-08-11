package todcloud.utils.io;

/**
 * Created by zhangjianxin on 2017/8/11.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**

 * @UserName : zhangjianxin

 * @DataTime : 2016年11月26日 下午10:29:35

 * @Description ：Please describe this document

 */
public class SK_Date {
    public static final long TIME_MILLISECOND = 1;

    public static final long TIME_SECOND = 1000 * TIME_MILLISECOND;

    public static final long TIME_MINUTE = 60 * TIME_SECOND;

    public static final long TIME_HOUR = 60 * TIME_MINUTE;

    public static final long TIME_DAY = 24 * TIME_HOUR;

    public static final long TIME_WEEK = 7 * TIME_DAY;

    // 时间格式化

    public static final String fmt_yyyy_MM_dd_HH_mm_ss_sss = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String fmt_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String fmt_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String fmt_yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    public static final String fmt_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String fmt_HH_mm_ss = "HH:mm:ss";
    public static final String fmt_HH_mm = "HH:mm";
    public static final String fmt_MM_dd_HH_mm = "MM-dd HH:mm";
    public static final String fmt_MM_dd = "MM-dd";
    public static final String fmtMMddHHmmss = "MMddHHmmss";
    public static final String fmtYyyy = "yyyy";
    public static final String fmtYyyyMm = "yyyyMM";
    public static final String fmtYyyyMmDd = "yyyyMMdd";

    public static Date addWeek(int w, Date date) {
        return addMillisSecond(w * TIME_WEEK, date);
    }

    public static Date addDay(int d, Date date) {
        return addMillisSecond(d * TIME_DAY, date);
    }

    public static Date addHour(int h, Date date) {
        return addMillisSecond(h * TIME_HOUR, date);
    }

    public static Date addMinute(int m, Date date) {
        return addMillisSecond(m * TIME_MINUTE, date);
    }

    public static Date addSecond(int s, Date date) {
        return addMillisSecond(s * TIME_SECOND, date);
    }

    public static Date addMillisSecond(long t, Date date) {
        date.setTime(date.getTime() + t);
        return date;
    }

    /**

     * 时间转字符串

     * @param format

     * @param date

     * @return

     */
    public static String formatString(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**

     * 字符串转时间

     * @param dateStr

     * @param formatStr

     * @return

     */
    public static Date formatDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}