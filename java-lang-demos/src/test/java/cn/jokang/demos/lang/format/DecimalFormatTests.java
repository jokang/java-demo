package cn.jokang.demos.lang.format;

import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

@Slf4j
public class DecimalFormatTests {


    @Test
    public void testDecimalFormat() {
        // 最多保留两位小数
        // DecimalFormat 带百分号的,不用另外乘以100
        DecimalFormat decimalFormat = new DecimalFormat("#.##%");
        System.out.println(decimalFormat.format(0.13539408789620833D));
        System.out.println(decimalFormat.format(0D));

        System.out.println(String.format("%.5f", 0.1234567D));
        System.out.println(String.format("%.5f", 0.1200000D));
        System.out.println(String.format("%.5f", 0.0012000D));
    }


    // 永远保留两位小数
    @Test
    public void testFileSizeDecimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(decimalFormat.format(0.13539408789620833D));
        System.out.println(decimalFormat.format(0D));
    }

    // 永远保留两位小数
    @Test
    public void testDecimalFormat2Digit() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        Assert.assertEquals(".14", decimalFormat.format(0.13539408789620833D));
        Assert.assertEquals(".00", decimalFormat.format(0D));
    }

    // BigDecimal的构造函数不接受null
    @Test(expected = NullPointerException.class)
    public void constructBigDecimalFromNull() {
        String s = null;
        BigDecimal bd = new BigDecimal(s);
        System.out.println(bd);
    }

    @Test
    public void testPercentage() {
        // 百分数字符串咋转成对应的double值?
        String value = "0.32708%";
//        double doubleValue = Double.parseDouble(value);
//        System.out.println(doubleValue);
        System.out.println(NumberUtils.isParsable(value));
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

    }

    @Test
    public void testReplace() {
        String s = "\\u003";
        System.out.println(s.replace("\\u003", ">"));


    }
}
