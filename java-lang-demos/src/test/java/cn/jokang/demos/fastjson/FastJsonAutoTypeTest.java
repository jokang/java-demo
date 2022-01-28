package cn.jokang.demos.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

/**
 * 测试fastjson 漏洞频出的autotype功能
 *
 * @author zhoukang
 * @date 2020-06-03
 */
public class FastJsonAutoTypeTest {

    @Test
    public void testAutoType() {
        Foo f = new Foo();
        f.setName("Foo");
        String jsonStr = JSON.toJSONString(f, SerializerFeature.WriteClassName);
        System.out.println(jsonStr);

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 静态内部类还不好使
        Foo o = (Foo) JSON.parse(jsonStr);
        System.out.println(o);

    }
}
