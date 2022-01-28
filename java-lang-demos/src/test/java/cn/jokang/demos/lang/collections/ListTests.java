package cn.jokang.demos.lang.collections;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

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
}
