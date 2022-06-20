package cn.jokang.demos.java8;

import org.junit.Test;

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
}