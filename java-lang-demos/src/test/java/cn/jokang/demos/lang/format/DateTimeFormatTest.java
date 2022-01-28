package cn.jokang.demos.lang.format;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhoukang
 * @date 2019-09-30
 */
@Slf4j
public class DateTimeFormatTest {

    // 最基本的格式化时间
    @Test
    public void testSimpleDateFormat() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formatter.parse("2019-07-11 12:01:02");
        log.info("{}", d);
    }

    // 不展示前缀0的格式
    @Test
    public void testDateFormat() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 7);
        c.set(Calendar.DAY_OF_MONTH, 10);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
        System.out.println(sdf.format(c.getTime()));
    }

    @Test
    public void testComplexDateFormatter() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'+08:00 yyyy", Locale.US);
        Date date = sdf.parse("Fri Jan 12 15:59:37 GMT+08:00 2018");
        log.info(date.toString());
    }
}
