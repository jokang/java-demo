package cn.jokang.demos.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhoukang
 * @date 2019-04-13
 */
@Slf4j
public class SplitterTest {
    @Test
    public void splitWithComma() {
        String orgIdsString = "1,2,3";
        List<String> idsString = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(orgIdsString);
        List<Integer> ids = idsString.stream().distinct().map(Integer::valueOf).collect(Collectors.toList());
        log.info(String.valueOf(ids));
    }

    // 不能传入Null
    @Test(expected = NullPointerException.class)
    public void splitNull() {
        String orgIdsString = null;
        List<String> idsString = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(orgIdsString);
        System.out.println(idsString);
    }

    // 空字符串返回的是空数组
    @Test
    public void splitEmptyString() {
        String orgIdsString = "";
        List<String> idsString = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(orgIdsString);

        Assert.assertEquals(Collections.emptyList(), idsString);
    }

    @Test
    public void splitSingle() {
        String orgIdsString = "1";
        List<String> idsString = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(orgIdsString);

        Assert.assertEquals(Collections.singletonList("1"), idsString);
    }

    // 逗号分隔获得的列表不能删除!!!
    @Test(expected = UnsupportedOperationException.class)
    public void testModify() {
        String s = "1,2,3";
        List<String> ignore = Lists.newArrayList("1", "2");
        List<String> ss = Splitter.on(",").trimResults().splitToList(s);
        ss.removeAll(Lists.newArrayList("1", "2"));
    }

    @Test
    public void spilitMap() {
        String tmp ="0_30001:1.8,0_30005:2,1_30001:1.2,1_30002:2,1_30003:1.2,1_30004:1.5,1_30006:2,2_30002:1.5,2_30003:2,2_30004:2,2_30007:2";
        Map<String, Double> weightMap = Maps.newHashMap();
        Splitter.on(",").withKeyValueSeparator(":").split(tmp).forEach((k, v) -> {
            weightMap.put(k, Double.valueOf(v));
        });
//        Splitter.on(":").withKeyValueSeparator(":").split()
    }
    
    @Test
    public void op() {
        // 注意三元表达式的自动拆装箱问题
//        Pair<Long, Long> value = Pair.of(1L, 1L);
        Pair<Long, Long> value = null;
        Long aorId = value == null ? -1L : value.getLeft();
        System.out.println(aorId);
    }

    @Test
    public void testStringEquals() {
        String s1 = "HELLO";
        String s2 = "HELLO";
        String s3 =  new String("HELLO");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1.equals(s2)); // true
        System.out.println(s1.equals(s3)); // true
    }
}
