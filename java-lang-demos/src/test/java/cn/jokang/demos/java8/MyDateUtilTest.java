package cn.jokang.demos.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.LocalDate;

public class MyDateUtilTest {

    @Test
    public void todayCompact() {
        System.out.println(MyDateUtil.todayCompact());
    }

    @Test
    public void parseDt() {
        System.out.println(MyDateUtil.parseDt("20220613"));
    }

    @Test
    public void localDateRange() {
        System.out.println(MyDateUtil.localDateRange(MyDateUtil.parseDt("20220601"), MyDateUtil.parseDt("20220606")));
    }

    @Test
    public void testSortLocalDate() {
        LocalDate d1 = MyDateUtil.parseDt("20210101");
        LocalDate d2 = MyDateUtil.parseDt("20220101");
        LocalDate d3 = MyDateUtil.parseDt("20230101");
        // LocalDate对象排序, 最新的日期排第一个
        Lists.newArrayList(d1, d2, d3).stream().max(LocalDate::compareTo)
            .ifPresent(System.out::println);
    }
}