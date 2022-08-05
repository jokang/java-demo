package cn.jokang.demos.collection;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @author zhoukang04
 * @date 2022/7/8
 */
public class LinkedHashSetTest {
    @Test
    public void linkedHashSet() {
        LinkedHashSet<Long> lhs = Sets.newLinkedHashSet();
        lhs.add(1L);
        lhs.add(1L);
        System.out.println(lhs);
    }

    @Test
    public void treeSet() {
        TreeSet<Long> lhs = Sets.newTreeSet();
        lhs.add(1L);
        lhs.add(1L);
        System.out.println(lhs);
    }
}
