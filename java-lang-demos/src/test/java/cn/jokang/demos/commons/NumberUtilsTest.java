package cn.jokang.demos.commons;

import junit.framework.Assert;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-11-24
 */
public class NumberUtilsTest {
    @Test
    public void simple() {
        Assert.assertTrue(NumberUtils.isDigits("1"));
        Assert.assertTrue(NumberUtils.isParsable("1"));

        Assert.assertFalse(NumberUtils.isDigits("0.1"));
        Assert.assertFalse(NumberUtils.isDigits(".1"));
        Assert.assertTrue(NumberUtils.isParsable("0.1"));
        Assert.assertTrue(NumberUtils.isParsable(".1"));
    }
}
