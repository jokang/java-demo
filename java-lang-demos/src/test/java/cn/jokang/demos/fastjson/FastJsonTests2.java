package cn.jokang.demos.fastjson;

import com.alibaba.fastjson.*;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FastJsonTests2 {
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
    }

    @Test
    public void testToJsonString() {
        Simple s = new Simple();
        s.setShouldTry(false);
        s.setXxx(true);
        String jsonString = JSONObject.toJSONString(s);
        System.out.println(jsonString);
    }

    /**
     * JSON.parseObject null返回null
     */
    @Test
    public void testParseNull() {
        List<String> s = JSON.parseObject(null, new TypeReference<List<String>>() {
        });
        Assert.assertNull(s);
    }

    /**
     * JSON.parseObject 空字符串抛异常
     */
    @Test(expected = JSONException.class)
    public void testParseEmptyString() {
        List<String> s = JSON.parseObject("", new TypeReference<List<String>>() {
        });
    }

    @Test
    public void testToJsonStringNull() {
        String js = JSON.toJSONString(null);
        Assert.assertEquals("null", js);
    }

    @Test
    public void testToJsonString2() {
        Map<String, Object> m = new HashMap<>();
        m.put("k1", 1573574401L);
        m.put("k2", 1573574401F);
        System.out.println(JSONObject.toJSONString(m));

        System.out.println(isLong("1"));
    }

    private static boolean isLong(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        if(StringUtils.isNumeric(str)){
            try{
                Long value = Long.parseLong(str);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    @Test
    public void test() {
        A jo = JSON.parseObject("{\"a\":\"1,2,3,\"}", A.class);
        System.out.println(jo);

    }

    @Data
    public static final class A {
        private Long a;
    }

}
