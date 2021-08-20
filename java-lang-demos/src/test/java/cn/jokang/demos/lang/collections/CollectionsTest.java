package cn.jokang.demos.lang.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhoukang04
 * @date 2021/8/20
 */
public class CollectionsTest {
    @Test
    public void testShuffle() {
        List<Integer> lst = IntStream.range(1, 20).boxed().collect(Collectors.toList());
        Collections.shuffle(lst);
        System.out.println(lst);
    }
}
