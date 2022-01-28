package cn.jokang.demos.lang.collections;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoukang
 * @date 2019-07-13
 */
@Slf4j
public class MapTest {
    @Test
    public void testMapToString() {
        Map<Integer, String> m = Maps.newHashMap();
        m.put(1, "a");
        m.put(2, "b");
        System.out.println(m);
    }

    @Test
    public void testNullElementOfHashMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(null, 1);
        map.put(1, null);
        log.info("{}", map);
    }

    @Test(expected = NullPointerException.class)
    public void testNullElementOfConcurrentHashMap() {
        // ConcurrentHashMap里面的key、value不能为null，HashMap里面的可以。
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(null, 1);
    }

    @Test
    public void testRemoveKeySet() {
        Map<String, String> m = Maps.newHashMap();
        m.put("k1", "v1");
        m.put("k2", "v2");
        System.out.println(m);

        m.keySet().remove("k1");
        System.out.println(m);
    }
}
