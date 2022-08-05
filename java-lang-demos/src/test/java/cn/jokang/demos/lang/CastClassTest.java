package cn.jokang.demos.lang;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhoukang04
 * @date 2022/8/5
 */
public class CastClassTest {
    @Test
    public void testCast() {
        Map<String, Object> container = Maps.newHashMap();
        // HashMap的key和value可以是null
        container.put("hi", null);
        container.put(null, "value");
        System.out.println(container);

        String value = getFromContainer(container, "hi");
        System.out.println(value);
    }

    @SuppressWarnings({"SameParameterValue", "unchecked"})
    private <T> T getFromContainer(Map<String, Object> container, String key) {
        return (T) container.get(key);
    }
}
