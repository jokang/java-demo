package cn.jokang.demos.lang;

import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-11-01
 */
public class MiscTest {
    @Test(expected = NullPointerException.class)
    public void test() {
        Integer aInteger = null;
        int aInt = 0;
        System.out.print(aInt != aInteger);
    }

    @Test
    public void showDefaultUncaughtExceptionHandler() {
        Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
        System.out.println(handler);
        Thread.UncaughtExceptionHandler handler2 = Thread.currentThread().getUncaughtExceptionHandler();
        System.out.println(handler2);
        handler2.uncaughtException(Thread.currentThread(), new Throwable("test"));
    }
}
