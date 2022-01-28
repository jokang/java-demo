package cn.jokang.demos.gson;


import com.google.common.collect.Lists;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zhoukang
 * @date 2020/11/20
 */
public class CustomGsonDserializerTest {
    static class AttributeScopeDeserializer implements JsonDeserializer<AttributeScope> {
        @Override
        public AttributeScope deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            AttributeScope[] scopes = AttributeScope.values();
            for (AttributeScope scope : scopes) {
                if (scope.scope.equals(json.getAsString()))
                    return scope;
            }
            return null;
        }
    }

    static class TruncateElement {
        int lower;
        int upper;
        String delimiter;
        List<AttributeScope> scope;
        AttributeScope s;
    }

    enum AttributeScope {
        TITLE("A"), DESCRIPTION("B");

        String scope;

        AttributeScope(String scope) {
            this.scope = scope;
        }
    }

    public static void main(String[] args) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AttributeScope.class, new AttributeScopeDeserializer());
        Gson gson = gsonBuilder.create();

        // Serialize
        TruncateElement ele = new TruncateElement();
        ele.delimiter = "sss";
        ele.lower = 1;
        ele.upper = 2;
        ele.scope = Lists.newArrayList(AttributeScope.DESCRIPTION, AttributeScope.TITLE);
        System.out.println(gson.toJson(ele));

        // Deserialize
        String jj = "{\"lower\":1,\"upper\":2,\"delimiter\":\"sss\",\"scope\":[\"A\",\"B\"],\"s\":\"A\"}";
        TruncateElement element = gson.fromJson(jj, TruncateElement.class);

        System.out.println(element.lower);
        System.out.println(element.upper);
        System.out.println(element.delimiter);
        System.out.println(element.scope.get(0));
        System.out.println(element.s);
    }
}


