package cn.jokang.demos.commons;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

/**
 * @author zhoukang
 * @date 2019-11-05
 */
public class CollectionUtilsTest {
    @Test(expected = IllegalArgumentException.class)
    public void sizeOfNull() {
        CollectionUtils.size(null);
    }
}
