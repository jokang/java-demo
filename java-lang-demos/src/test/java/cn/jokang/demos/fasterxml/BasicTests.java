package cn.jokang.demos.fasterxml;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Jackson 1.x 版本的包名是org.codehaus.jackson，
 * 2.x 版本的包名变为com.fasterxml.jackson
 */
public class BasicTests {
    static final class Simple {
        @JsonProperty("desc")
        private String description;

        public Simple(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @Test
    public void basicTest() throws IOException {
        Simple s = new Simple("simple");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(s));

        Map<String, Object> m = new HashMap<>();
        m.put("data", s);
        System.out.println(mapper.writeValueAsString(m));

        JSONObject jo = new JSONObject();
        jo.put("data", s);
        System.out.println(mapper.writeValueAsString(jo));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(out, JsonEncoding.UTF8);
        mapper.writeValue(jsonGenerator, jo);
        System.out.println(out.toString("UTF-8"));
    }
}
