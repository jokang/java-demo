package cn.jokang.demos.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateTimeTest {
    @Test
    public void test() {
        log.info("TimeUnit.SECONDS.toHours(3700)\n" + TimeUnit.SECONDS.toHours(3700));
    }

    @Test
    public void test2() {
        Long beginDate = 1554048000L;
        Long endDate = 1555257599L;
        System.out.println(endDate - beginDate > 60 * 24 * 60 * 60);
    }



    @Test
    public void testCalendarMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(c.getTime()));

        // 月份从0开始, 1是二月
        c.set(Calendar.MONTH, 1);
        System.out.println(formatter.format(c.getTime()));
    }


}
