package cn.jokang.demos.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NumberTests {
    private static final Logger logger = LoggerFactory.getLogger(NumberTests.class);

    @Test
    public void test() {
        long a = 19856299051020288L;
        int b = (int) a;
        long c = (long) b;
        System.out.println(b);
        System.out.println(c);

        float floatA = (float) a;
        System.out.println((long) a);
    }

    @Test
    public void testLog() {
        Exception e = new Exception("TestException");
        logger.error("marker here {} ", 12, e);
    }

    @Test
    public void testMaxLong() {
        logger.info(String.valueOf(Long.MAX_VALUE));
        logger.info("" + String.valueOf(Long.MAX_VALUE).length());
    }

    @Test
    public void testAsList() {
        List<String> s = Arrays.asList("1,2".split(","));
        System.out.println(s);
    }

    @Test
    public void random() {
        long start = System.currentTimeMillis();
        for (int i = 0; i<1000; i++) {
            Random r = new Random();
            List<Integer> collect = r.ints(200, 0, 200000).boxed().collect(Collectors.toList());
        }
        long end = System.currentTimeMillis();
        System.out.println("time used: " + (end - start));
    }
}
