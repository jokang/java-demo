package cn.jokang.demos.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-08-28
 */
@Slf4j
public class NumberTest {
    @Test
    public void testMaxMinInteger() {
        // Integer的最大值21亿+
        log.info("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        log.info("Integer.MIN_VALUE=" + Integer.MIN_VALUE);
    }

    // Flat和Double的MIN_VALUE都是正值...
    @Test
    public void testMaxMinFloat() {
        log.info("Float.MAX_VALUE={}", Float.MAX_VALUE);
        log.info("Float.MIN_VALUE={}", Float.MIN_VALUE);
    }

    // Flat和Double的MIN_VALUE都是正值...
    @Test
    public void testMaxMinDouble() {
        log.info("Double.MAX_VALUE={}", Double.MAX_VALUE);
        log.info("Double.MIN_VALUE={}", Double.MIN_VALUE);
    }

    //除法精度
    @Test
    public void testFloat() {
        log.info("{}", 1f / 9057);
    }

    @Test
    public void testFloatCompare() {
        // Float比较有误差
        float f1 = 1573574401F;
        float f2 = 1573574400F;
        Assert.assertTrue(f1 == f2);
    }

    @Test
    public void testNegativeByte() {
        byte negativeByte = (byte) -1;
        log.info("negative byte -1 is {}", negativeByte);
    }

    @Test
    public void testOctal() {
        // 0开头的字段表示8进制数. 处理09:01:02这种时间的数据的时候需要注意下.
        int octal = 031;

        System.out.println(octal); // 25
        System.out.println(Integer.parseInt("09")); // 9
        System.out.println(Integer.parseInt("031")); // 其实还是31

    }
}
