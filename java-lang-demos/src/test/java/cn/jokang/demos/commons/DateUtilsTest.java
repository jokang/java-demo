package cn.jokang.demos.commons;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
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
}
