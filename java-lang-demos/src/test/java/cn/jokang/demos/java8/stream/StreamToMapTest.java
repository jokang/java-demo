package cn.jokang.demos.java8.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhoukang
 * @date 2019-07-13
 */
public class StreamToMapTest {
    @Test
    public void testToMap() {
        List<Integer> lst = Lists.newArrayList(1, 2);
        Map<Integer, Integer> result =
            lst.stream().collect(Collectors.toMap(Function.identity(), x -> x + 1));
        Map<Integer, Integer> expected = Maps.newHashMap();
        expected.put(1, 2);
        expected.put(2, 3);
        Assert.assertEquals(expected, result);
    }

    // 有重复key、没有merge函数的时候，抛异常。
    @Test(expected = IllegalStateException.class)
    public void testDuplicatedKeys() {
        List<Integer> lst = Lists.newArrayList(1, 1, 2);
        Map<Integer, Integer> result =
            lst.stream().collect(Collectors.toMap(Function.identity(), x -> x + 1));
    }

    @Test
    public void testMerge() {
        List<Integer> lst = Lists.newArrayList(1, 1, 2);
        Map<Integer, Integer> result =
            lst.stream().collect(Collectors.toMap(Function.identity(), x -> x + 1, (v1, v2) -> v1 + v2));

        Map<Integer, Integer> expected = Maps.newHashMap();
        expected.put(1, 4);
        expected.put(2, 3);
        Assert.assertEquals(expected, result);

    }

    @Data
    @AllArgsConstructor
    class Vo {
        private Integer key;
        private String value;
    }

    @Test
    public void testNullKey() {
        List<Vo> lst = Lists.newArrayList(
            new Vo(1, "v1"),
            new Vo(null, "v2")
        );
        Map<Integer, String> m = lst.stream()
            .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
        System.out.println(m);
    }
}
