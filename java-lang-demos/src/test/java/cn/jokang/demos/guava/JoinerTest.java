package cn.jokang.demos.guava;

import com.google.common.base.Joiner;
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
public class JoinerTest {
    @Test(expected = NullPointerException.class)
    public void joinNull() {
        List<String> ss = null;
        Joiner.on(",").skipNulls().join(ss);
    }

    @Test
    public void joinEmpty() {
        List<String> empty = Collections.emptyList();
        Assert.assertEquals("", Joiner.on(",").skipNulls().join(empty));
    }

    @Test
    public void joinSingle() {
        List<String> empty = Collections.singletonList("1");
        Assert.assertEquals("1", Joiner.on(",").skipNulls().join(empty));
    }

    @Test
    public void joinBasic() {
        List<String> empty = Lists.newArrayList("1", "2", "3");
        Assert.assertEquals("1,2,3", Joiner.on(",").skipNulls().join(empty));
    }
}
