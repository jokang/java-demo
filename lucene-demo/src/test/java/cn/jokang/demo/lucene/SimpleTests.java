package cn.jokang.demo.lucene;

import org.apache.lucene.util.SmallFloat;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2020-04-24
 */
public class SimpleTests {
    @Test
    public void test() {
        //0.625
        System.out.println(SmallFloat.byte315ToFloat(new Integer(121).byteValue()));
    }
}
