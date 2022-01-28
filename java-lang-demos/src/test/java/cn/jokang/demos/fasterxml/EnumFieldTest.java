package cn.jokang.demos.fasterxml;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * 在枚举的字段或者无参方法上加上@JsonValue注解,序列化和反序列化都能work.
 * POJO的反序列化需要用@JsonCreator注解
 *
 * @author zhoukang
 * @date 2019-10-23
 */
public class EnumFieldTest {
    enum Status {
        VALID(1),
        INVALID(0);

        @JsonValue
        int value;

        Status(int value) {
            this.value = value;
        }

        // @JsonValue
        int jsonValue() {
            return value;
        }
    }

    @Test
    public void testSerialize() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Status> o = Maps.newHashMap();
        o.put("k1", Status.VALID);
        o.put("k2", Status.INVALID);

        System.out.println(om.writeValueAsString(o));
    }

    @Test
    public void testDeserialize() throws IOException {
        String json = "{\"k1\":1,\"k2\":0}";
        ObjectMapper om = new ObjectMapper();
        Map<String, Status> o = om.readValue(json, new TypeReference<Map<String, Status>>() {
        });
        System.out.println(o);
    }
}
