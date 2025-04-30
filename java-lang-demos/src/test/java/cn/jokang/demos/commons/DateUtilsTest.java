package cn.jokang.demos.commons;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhoukang
 * @date 2020-02-21
 */
public class DateUtilsTest {
    @Test
    public void testAddMonth() {
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());
        c.set(2020, 0, 30);
        c.add(Calendar.MONTH, 1);
        System.out.println(c.getTime());
    }

    @Test
    public void testGenerateBetween() throws ParseException {
        Date d = DateUtils.parseDate("20210811", "yyyyMMdd");
        Date yesterday = DateUtils.addDays(d, -1);
        System.out.println(DateFormatUtils.format(d, "yyyyMMdd hh:mm:ss"));
        System.out.println(DateFormatUtils.format(yesterday, "yyyyMMdd hh:mm:ss"));
    }

    @Test
    public void test1() {
        List<String> ll = Lists.newArrayList("a", "b");
        String md5PrefixString = Joiner.on("','").skipNulls().join(ll);
        System.out.println(md5PrefixString);
    }

    @Test
    public void test2() {
        System.out.println(getGapBetweenDays("20240101", "20240102"));
    }

    private static int getGapBetweenDays(String startDateStr, String endDateStr) {
        java.util.Date endDate = string2DateDay(endDateStr);
        java.util.Date startDate = string2DateDay(startDateStr);
        return (int)((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000)); // 相差天数
    }

    private static Date string2DateDay(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        str = null2Trim(str);
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }
    }

    public static final String null2Trim(String str) {
        return str == null ? "" : str.trim();
    }
}
