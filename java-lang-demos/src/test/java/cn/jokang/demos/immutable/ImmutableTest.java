package cn.jokang.demos.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zhoukang04
 * @date 2021/4/29
 */
public class ImmutableTest {
    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableList() {
        List<Long> ids = Lists.newArrayList(1L, 2L, 3L);
        List<Long> unmodifiable = Collections.unmodifiableList(ids);
        unmodifiable.remove(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListIterator() {
        List<Long> ids = Lists.newArrayList(1L, 2L, 3L);
        List<Long> unmodifiable = Collections.unmodifiableList(ids);
        ListIterator<Long> listIterator = unmodifiable.listIterator();
        listIterator.next();
        listIterator.remove();
        System.out.println(ids);
    }

    @Test
    public void testImmutableList() {
        List<Long> ids = Lists.newArrayList(1L, 2L, 3L);
        List<Long> immutable = ImmutableList.copyOf(ids);
    }
}
