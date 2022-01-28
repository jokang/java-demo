package cn.jokang.demos.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-03-25
 */
@Slf4j
public class FastJsonTests {
    /**
     * fastjson反序列化之后的Map对象可变
     */
    @Test
    public void testModifyMap() {
        String json = "{\"key\":\"value\"}";
        Map<String, String> m = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        log.info(String.valueOf(m));
        m.put("anotherKey", "anotherValue");
        log.info(String.valueOf(m));
    }

    @Test
    public void testParseListOfList() {
        String json = "{\"k\":[[1,2],[3,4]]}";
        JSONObject jo = JSON.parseObject(json);
    }

    @Test
    public void testCreateJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("lst", Lists.newArrayList("a", "b"));
        log.info("jsonObj={}", jsonObj.toJSONString());
    }
}
