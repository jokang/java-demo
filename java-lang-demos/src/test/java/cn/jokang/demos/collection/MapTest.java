package cn.jokang.demos.collection;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoukang04
 * @date 2022/7/8
 */
public class MapTest {
    @Test(expected = NullPointerException.class)
    public void testPutNullValueToConcurrentHashMap() {
        // ConcurrentHashMap不支持null作为value, 会抛空指针
        ConcurrentHashMap<String, String> cm = new ConcurrentHashMap<>();
        Map<String, String> mapWithNullValues = Maps.newHashMap();
        mapWithNullValues.put("k", null);
        cm.putAll(mapWithNullValues);
    }
}
