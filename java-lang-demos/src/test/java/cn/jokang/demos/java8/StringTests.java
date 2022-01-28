package cn.jokang.demos.java8;

import org.junit.Assert;
import org.junit.Test;

public class StringTests {
    @Test
    public void testStringReplaceAll() {
        String s = "test_table_table".replaceAll("_table$", "");
        Assert.assertEquals("test_table", s);

        s = "test_table".replaceAll("_table$", "");
        Assert.assertEquals("test", s);
    }
}
