package cn.jokang.demos.fastjson;

import com.alibaba.fastjson.*;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

@Slf4j
public class FastJsonTests1 {
    class Simple {
        private Boolean shouldTry;
        private Boolean xxx;

        public Boolean isXxx() {
            return xxx;
        }

        public void setXxx(Boolean xxx) {
            this.xxx = xxx;
        }

        public Boolean getShouldTry() {
            return shouldTry;
        }

        public void setShouldTry(Boolean shouldTry) {
            this.shouldTry = shouldTry;
        }
    }

    @Test
    public void testJsonArray() {
        JSONArray jsonArray = JSON.parseArray("");
        System.out.println(jsonArray);

        //noinspection ConstantConditions
        jsonArray = JSON.parseArray(null);
        System.out.println(jsonArray);

        List<Long> longLst = JSON.parseArray("[1, 2L, 3L, 2147483647, 2147483647L]", Long.class);
        System.out.println(longLst);

        System.out.println(Integer.MAX_VALUE);

        longLst = JSON.parseArray("[]", Long.class);
        System.out.println(longLst);

        int i = 1000 * 60 * 60 * 24 * 7;
//        System.out.println(;
    }

    @Test
    public void testToJsonString() {
        Simple s = new Simple();
        s.setShouldTry(false);
        s.setXxx(true);
        String jsonString = JSONObject.toJSONString(s);
        System.out.println(jsonString);
    }

    @Test
    public void testParseNull() {
        List<String> s = JSON.parseObject(null, new TypeReference<List<String>>() {
        });
        Assert.assertNull(s);
    }

    @Test(expected = JSONException.class)
    public void testParseEmptyString() {
        List<String> s = JSON.parseObject("", new TypeReference<List<String>>() {
        });
    }

    @Test
    public void testMaxInteger() {
        // Integer的最大值21亿+
        log.info("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
    }

    // double类型的数字,即使JSON给前端的是1.0,
    // 前端用JSON.parse解出来,显示的结果是1
    @Test
    public void testDouble() {
        Map<String, Double> m = Maps.newHashMap();
        m.put("k", 1.0);
        System.out.println(JSON.toJSONString(m));
    }
}
