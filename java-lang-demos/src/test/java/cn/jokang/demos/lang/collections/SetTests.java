package cn.jokang.demos.lang.collections;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

/**
 * @author zhoukang04
 * @date 2023/6/25
 */
public class SetTests {
    @Test
    public void testAddEmptySet() {
        Set<String> s = Sets.newHashSet();
        s.addAll(Sets.newHashSet());
        System.out.println(s);
    }
}
