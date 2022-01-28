package cn.jokang.demos.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-05-15
 */
@Slf4j
public class StringTest {
    @Test
    public void formatTest() {
        log.info(String.format("%s %d %d", "String", 1, 2L));
        log.info(String.format("%s %s %s", "String", 1, 2L));
    }

    @Test
    public void testReplace() {
        String s = "a";
        s.replace("a", null);
    }

    @Test()
    public void testLiteralDecimal() {
        // Java默认的浮点数常量是double类型。
        // float  f = 3.4;
        float f = 3.4F;
    }

    @Test(expected = NullPointerException.class)
    public void testValueOfNull() {
        System.out.println(String.valueOf(null));
    }

    // 字符串的20排在100后面,因为字符串默认是字典序
    @Test
    public void stringCompare() {
        log.info("{}", "20".compareTo("100"));
        log.info("{}", Integer.compare(20, 100));
    }

    // String.valueOf不能为null
    // https://www.cnblogs.com/lingiu/p/3224431.html
    @Test(expected = NullPointerException.class)
    public void stringValueOfNull() {
        System.out.println(String.valueOf(null));
    }

    @Test
    public void testStringEquals() {
        String a = new String("");
        System.out.println(a == "");
    }
}
