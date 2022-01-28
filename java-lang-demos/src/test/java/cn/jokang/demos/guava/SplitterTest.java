package cn.jokang.demos.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
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
//        Splitter.on(":").withKeyValueSeparator(":").split()
    }
}
