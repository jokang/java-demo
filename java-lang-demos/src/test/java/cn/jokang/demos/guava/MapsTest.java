package cn.jokang.demos.guava;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-07-13
 */
public class MapsTest {
    @Test
    public void initializeMap() {
        Map<Integer, String> map = ImmutableMap.of(1, "1", 2, "2");
        map = ImmutableMap.<Integer, String>builder().put(1, "1").put(2, "2").build();
    }
}
