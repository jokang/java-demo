package cn.jokang.demos.gson;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author jokang
 * @date 2021/3/2
 */
public class GsonTests {
    private final static Gson GSON = new Gson();
    public static final Type STRING_STRING_MAP_TYPE_TOKEN = new TypeToken<Map<String, String>>() {
    }.getType();


    @Test
    public void testSerializeNull() {
        String res = GSON.toJson(null);
        // null对象 被序列化成 "null" 字符串了
        Assert.assertEquals("null", res);
    }

    @Test
    public void testSerializeEmptyMap() {
        Map<String, String> data = Maps.newHashMap();
        String res = GSON.toJson(data);
        Assert.assertEquals("{}", res);
    }

    @Test
    public void testSerializeEmptyList() {
        List<String> data = Lists.newArrayList();
        String res = GSON.toJson(data);
        Assert.assertEquals("[]", res);
    }

    @Test
    public void testDeserializeToMap() {
        @SuppressWarnings("ConstantConditions")
        Map<String, String> m1 = GSON.fromJson((String) null, STRING_STRING_MAP_TYPE_TOKEN);
        Assert.assertNull(m1);

        Map<String, String> m2 = GSON.fromJson("", STRING_STRING_MAP_TYPE_TOKEN);
        Assert.assertNull(m2);

        Map<String, String> m3 = GSON.fromJson("{}", STRING_STRING_MAP_TYPE_TOKEN);
        Assert.assertNotNull(m3);

        Map<String, String> m4 = GSON.fromJson("null", STRING_STRING_MAP_TYPE_TOKEN);
        Assert.assertNull(m4);
    }

    @Test
    public void testEscaping() {
        Map<String, String> m1 = Maps.newHashMap();
        m1.put("k", ">");

        Gson escapingGson = new Gson();
        // HTML 特殊字符将会被转义
        Assert.assertEquals("{\"k\":\"\\u003e\"}", escapingGson.toJson(m1));
        Map<String, String> m2 = escapingGson.fromJson("{\"k\":\"\\u003e\"}", STRING_STRING_MAP_TYPE_TOKEN);
        // HTML 特殊字符会被反序列化回来
        Assert.assertEquals(m1, m2);

        Gson nonescapingGson = new GsonBuilder().disableHtmlEscaping().create();
        // 关闭HTML转义之后, 不会被转义
        Assert.assertEquals("{\"k\":\">\"}", nonescapingGson.toJson(m1));
        Map<String, String> m3 = nonescapingGson.fromJson("{\"k\":\"\\u003e\"}", STRING_STRING_MAP_TYPE_TOKEN);
        // 关闭了HTML转义之后, HTML 特殊字符会被反序列化回来
        Assert.assertEquals(m1, m3);
    }

    // JSON 有重复key会抛错
    @Test(expected = JsonSyntaxException.class)
    public void testDuplicatedKey() {
        Gson gson = new Gson();
        Map<String, String> result = gson.fromJson("{\"k\": false, \"k\":true}", STRING_STRING_MAP_TYPE_TOKEN);
    }

    @Test
    public void testSerializeToStringMap() {
        Gson gson = new Gson();
        Map<String, String> result = gson.fromJson("{\"k\": 1}", STRING_STRING_MAP_TYPE_TOKEN);
        // 即使JSON里面的value不是string, 也能反序列化
        Assert.assertEquals(ImmutableMap.of("k", "1"), result);
    }

    @Test
    public void testGetAsLong() {
        String extraJson = "{\"page_stay_time\":11750,\"action_dt\":20210802}";
        if (StringUtils.isNotEmpty(extraJson)) {
            JsonElement extraJsonRoot = JsonParser.parseString(extraJson);
            System.out.println(extraJsonRoot.getAsJsonObject().get("page_stay_time").getAsLong());
        }
    }
}
