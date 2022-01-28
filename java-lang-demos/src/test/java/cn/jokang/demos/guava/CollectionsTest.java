package cn.jokang.demos.guava;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-03-22
 */
@Slf4j
public class CollectionsTest {
    @Test
    public void testUniqueIndex() {
        String[] fieldNames = {"fieldA_table.attrA", "fieldB_table.attrB"};
        Map<String, String> tagTableFieldMap = Maps.uniqueIndex(Arrays.asList(fieldNames),
            new Function<String, String>() {
                @Override
                public String apply(String s) {
                    return s.split("\\.")[0];
                }
            });
        log.info(JSON.toJSONString(tagTableFieldMap));
    }

    @Test
    public void fromJsonString() {
        String stringArray = "[\"A\", \"B\"]";
        JSONArray arr = JSON.parseArray(stringArray);
        Assert.assertTrue(arr.get(0) instanceof String);

        String numberArray = "[1.0, 2, 3]";
        JSONArray arr2 = JSON.parseArray(numberArray);
        System.out.println(arr2.get(0).getClass());
        System.out.println(numberArray);

        String value = "ss";
        JSONArray arr3 = JSON.parseArray(value);
        System.out.println(arr3);
    }
}
