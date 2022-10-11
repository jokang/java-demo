package cn.jokang.demos.lang.collections;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

/**
 * @author zhoukang
 * @date 2019-03-26
 */
@Slf4j
public class ListTests {

    @Test
    public void testListToString() {
        List<String> ss = Lists.newArrayList("a", "b", "c");
        System.out.println(ss.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListTest() {
        //List#subList如果toIndex大于List大小，会抛NPE。
        // 还要注意subList返回的是原列表的视图，有内存泄露的风险，
        // 而且不要在返回的结果上调用insert等方法。
        List<String> lst = Lists.newArrayList("a", "b", "c");
        lst.subList(0, 4);
        log.info(String.valueOf(lst));
    }

    @Test
    public void streamForEachTest() {
        List<String> lst = Lists.newArrayList("a", "b", "c");
        lst.forEach(x -> log.info(x));
    }

    @Test
    public void addNull() {
        // ArrayList 可以添加null
        List<String> lst = Lists.newArrayList();
        lst.add(null);
        log.info(String.valueOf(lst));
    }

    @Test
    public void addAllWithNull() {
        // ArrayList 可以添加null
        List<String> targetList = Lists.newArrayList();
        List<String> nullList = Lists.newArrayList(null, null);
        targetList.addAll(nullList);
    }

    @Test
    public void testInsertInStream() {
        ArrayList<Integer> lst = Lists.newArrayList(1, 2, 3, 4);
        try {
            lst.stream().forEach(i -> {
                lst.add(i + 1);
                System.out.println(lst);
            });
        } catch (ConcurrentModificationException e) {
            //ignore
            // 实际上末尾还是加成功了
        }
    }

    @Test
    public void testListIterator() {
        ArrayList<Integer> lst = Lists.newArrayList(1, 2, 3, 4);
        // ListIterator的操作更多, 也会报一些并发修改的错误
        ListIterator<Integer> lstItr = lst.listIterator();
        while (lstItr.hasNext()) {
            Integer next = lstItr.next();
            lstItr.add(100);
            lstItr.remove();
            lstItr.set(2);
        }
        System.out.println(lst);
    }

    @Test
    public void testSkipInsert() {
        // 测试插入空列表
        ArrayList<String> lst = Lists.newArrayList();
        skipInsert(lst, 0, "a", s -> false);
        Assert.assertEquals(Lists.newArrayList("a"), lst);

        lst = Lists.newArrayList();
        skipInsert(lst, 0, "a", s -> true);
        Assert.assertEquals(Lists.newArrayList("a"), lst);

        // 一个元素列表-首位&无固定位
        lst = Lists.newArrayList("a");
        skipInsert(lst, 0, "x", s -> false);
        Assert.assertEquals(Lists.newArrayList("x", "a"), lst);

        // 一个元素列表-末尾&无固定位
        lst = Lists.newArrayList("a");
        skipInsert(lst, 1, "x", s -> false);
        Assert.assertEquals(Lists.newArrayList("a", "x"), lst);

        // 一个元素列表-首位,有固定位
        try {
            lst = Lists.newArrayList("fix");
            skipInsert(lst, 0, "b", s -> "fix".equals(s));
        } catch (IllegalArgumentException e) {
            //ignore
        }

        // 一个元素列表-末尾,无固定位
        lst = Lists.newArrayList("fix");
        skipInsert(lst, 1, "x", s -> "fix".equals(s));
        Assert.assertEquals(Lists.newArrayList("fix", "x"), lst);

        // 四个元素列表-无固定位
        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 0, "x", s -> false);
        Assert.assertEquals(Lists.newArrayList("x", "a", "b", "c", "d"), lst);

        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 1, "x", s -> false);
        Assert.assertEquals(Lists.newArrayList("a", "x", "b", "c", "d"), lst);

        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 4, "x", s -> false);
        Assert.assertEquals(Lists.newArrayList("a", "b", "c", "d", "x"), lst);

        // 四个元素列表-一个固定位
        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 1, "x", s -> "a".equals(s));
        Assert.assertEquals(Lists.newArrayList("a", "x", "b", "c", "d"), lst);

        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 4, "x", s -> "a".equals(s));
        Assert.assertEquals(Lists.newArrayList("a", "b", "c", "d", "x"), lst);

        // 四个元素列表-两个个固定位
        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 1, "x", s -> "a".equals(s) || "d".equals(s));
        Assert.assertEquals(Lists.newArrayList("a", "x", "b", "d", "c"), lst);

        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 2, "x", s -> "a".equals(s) || "d".equals(s));
        Assert.assertEquals(Lists.newArrayList("a", "b", "x", "d", "c"), lst);

        lst = Lists.newArrayList("a", "b", "c", "d");
        skipInsert(lst, 4, "x", s -> "a".equals(s) || "d".equals(s));
        Assert.assertEquals(Lists.newArrayList("a", "b", "c", "d", "x"), lst);
    }

    public static <T> boolean skipInsert(ArrayList<T> lst, int idx, T item, Predicate<T> shouldSkip) {
        if (null == shouldSkip) {
            throw new IllegalArgumentException("fixIdxTest is null");
        }
        if (idx < 0 || idx > lst.size()) {
            // idx==lst.size()是合法值, 说明是插入到最后
            throw new IndexOutOfBoundsException("Index: " + idx + ", Size: " + lst.size());
        }

        if (idx < lst.size() && shouldSkip.test(lst.get(idx))) {
            return false;
        }

        // 往后挪,跳过固定位置
        final int originalSize = lst.size();
        int currentIdx = idx;
        T itemToInsert = item;
        while (currentIdx < originalSize) {
            // 寻找下一个可插入位置
            T currentItem = lst.get(currentIdx);
            if (shouldSkip.test(currentItem)) {
                currentIdx++;
                continue;
            }

            lst.set(currentIdx, itemToInsert);

            // 维护循环变量
            currentIdx++;
            itemToInsert = currentItem;
        }
        lst.add(itemToInsert);

        return true;
    }

    public static <T> boolean skipForward(List<T> lst, int idx, int fromIdx, Predicate<T> shouldSkip) {
        if (null == shouldSkip) {
            shouldSkip = t -> false;
        }
        if ((idx < 0 || idx >= lst.size()) || (fromIdx < 0 || fromIdx >= lst.size())) {
            // idx==lst.size()是合法值, 说明是插入到最后
            throw new IndexOutOfBoundsException(String.format("fromIdx=%d toIdx=%d size=%d", fromIdx, idx, lst.size()));
        }
        if (shouldSkip.test(lst.get(fromIdx))) {
            return false;
        }

        if (shouldSkip.test(lst.get(idx))) {
            return false;
        }

        if (fromIdx <= idx) {
            return false;
        }

        // 往后挪,跳过固定位置
        // 只需要移动fromIdx到toIdx之间的元素
        int currentIdx = idx;
        T itemToInsert = lst.get(fromIdx);
        while (currentIdx <= fromIdx) {
            // 寻找下一个可插入位置
            T currentItem = lst.get(currentIdx);
            if (shouldSkip.test(currentItem)) {
                currentIdx++;
                continue;
            }

            lst.set(currentIdx, itemToInsert);

            // 维护循环变量
            currentIdx++;
            itemToInsert = currentItem;

        }
        return true;
    }

    @Test
    public void testMoveForward() {
        List<String> lst;
        lst = Lists.newArrayList("0", "1", "2", "3", "4", "5");
        skipForward(lst, 2, 5, null);
        System.out.println(lst);
    }

}
