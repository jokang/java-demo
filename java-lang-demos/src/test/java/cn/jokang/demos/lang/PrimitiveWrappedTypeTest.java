package cn.jokang.demos.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-03-27
 */
@Slf4j
public class PrimitiveWrappedTypeTest {
    private void methodAcceptPrimitive(int i) {
        log.info(String.valueOf(i));
    }

    @Test(expected = NullPointerException.class)
    public void test() {
        Integer i = null;
        //noinspection ConstantConditions
        methodAcceptPrimitive(i);
    }
}
