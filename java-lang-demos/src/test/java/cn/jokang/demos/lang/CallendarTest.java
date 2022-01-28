package cn.jokang.demos.lang;

import org.junit.Test;

import java.util.Calendar;

/**
 * @author zhoukang
 * @date 2020/7/13
 */
public class CallendarTest {
    @Test
    public void test() {
        int i = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        System.out.println(i);
        System.out.println(i % 5);
    }
}
