package cn.jokang.demos.collection;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
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

    @Test(expected = ConcurrentModificationException.class)
    public void testHashMapDeleteWhileIteration() {
        Map<String, String> map = Maps.newHashMap();
        map.put("k1", "v1");
        for (Map.Entry<String, String> ent : map.entrySet()) {
            map.remove(ent.getKey());
        }
    }

    @Test
    public void testHashMapDeleteWhileIteration2() {
        Map<String, String> map = Maps.newHashMap();
        map.put("k1", "v1");
        Set<String> keys = map.keySet();
        for (String k : keys) {
            map.remove(k);
        }
    }
}
