package cn.jokang.demos.lang.format;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;

/**
 * https://vence.github.io/2016/04/29/javamethod-messageformat/
 *
 * @author zhoukang
 * @date 2019-09-28
 */
public class MessageFormatTest {
    // 得指定{}里的序号,从0开始,和Python里面不一样
    @Test(expected = IllegalArgumentException.class)
    public void formatException() {
        System.out.println(MessageFormat.format("{}", 1));
    }

    @Test
    public void formatInt() {
        System.out.println(MessageFormat.format("{0}", 1));
    }

    // 格式化数字的时候要小心千位后的逗号
    @Test
    public void formatThousandInt() {
        System.out.println(MessageFormat.format("{0}", 1000));
        System.out.println(MessageFormat.format("{0,number,integer}", 1000));
        System.out.println(MessageFormat.format("{0,number,#}", 1000));
    }

    // 大括号里的索引从0开始
    @Test
    public void formatDecimal() {
        System.out.println(MessageFormat.format("{0,number,0.00}", 1.1));
    }

    @Test
    public void formatInteger() {
        System.out.println(MessageFormat.format("{0,number,integer}", 101));
    }

    @Test
    public void formatDate() {
        System.out.println(MessageFormat.format("时间:{0,time,M月d日}", new Date()));
    }

    @Test
    public void formatSkip() {
        System.out.println(MessageFormat.format("{1}", null, 1));
    }
}
