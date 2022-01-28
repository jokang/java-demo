package cn.jokang.demos.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhoukang
 * @date 2019-04-10
 */
public class RegexpDemo {

    @Test
    public void test2() {
        Pattern p = Pattern.compile("(^L[012345]$)|(^L[012345]_\\d+$)");
        Assert.assertTrue(p.matcher("L0").matches());
        Assert.assertTrue(p.matcher("L1").matches());
        Assert.assertTrue(p.matcher("L2").matches());
        Assert.assertTrue(p.matcher("L3").matches());
        Assert.assertTrue(p.matcher("L4").matches());
        Assert.assertTrue(p.matcher("L5").matches());

        Assert.assertTrue(p.matcher("L0_1").matches());
        Assert.assertTrue(p.matcher("L1_10").matches());
        Assert.assertTrue(p.matcher("L2_100").matches());
        Assert.assertTrue(p.matcher("L3_1000").matches());
        Assert.assertTrue(p.matcher("L4_10000").matches());
        Assert.assertTrue(p.matcher("L5_100000").matches());
    }
}
