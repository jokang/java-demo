package cn.jokang.demo.aviator.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * @author zhoukang04
 * @date 2021/7/27
 */
public class EvaluationTest {
    @Test
    public void testBooleanEquals() {
        Expression expression = AviatorEvaluator.getInstance().compile("val == true");
        Map<String, Object> param = Maps.newHashMap();
        param.put("val", true);
        Object result = expression.execute(param);
        System.out.println(result);

        param = Maps.newHashMap();
        param.put("val", null);
        Object result2 = expression.execute(param);
        System.out.println(result2);
    }

    @Test
    public void testStringEquals() {
        Expression expression = AviatorEvaluator.getInstance().compile("val == 'aaa'");
        Map<String, Object> param = Maps.newHashMap();
        param.put("val", "aaa");
        Object result = expression.execute(param);
        System.out.println(result);

        param = Maps.newHashMap();
        param.put("val", null);
        Object result2 = expression.execute(param);
        System.out.println(result2);
    }

    @Test
    public void testInSet() {
        Expression expression = AviatorEvaluator.getInstance().compile("include(spu.rawCoreWords, '牙刷') ? '婴儿牙刷' : (include(spu.rawCoreWords, '牙膏') ?'婴儿牙膏':'')");
        Map<String, Object> param = Maps.newHashMap();
        param.put("spu", ImmutableMap.of("rawCoreWords", Sets.newHashSet("牙膏")));
        Object result = expression.execute(param);
        System.out.println(result);
    }

    @Test
    public void testRegexp() {
        Expression expression = AviatorEvaluator.getInstance().compile("spu.spuName=~/.*(烤鸭).*/ ? $1:''");
        Map<String, Object> param = Maps.newHashMap();
        param.put("spu", ImmutableMap.of("rawCoreWords", Sets.newHashSet("牙膏"), "spuName", "全聚德 北京特产 百年烤鸭礼盒含饼酱1380g"));
        Object result = expression.execute(param);
        System.out.println(result);
    }

    @Test
    public void testReturn() {
        Expression expression = AviatorEvaluator.getInstance()
            .compile("return data;");
        Map<String, Object> param = Maps.newHashMap();
        param.put("data", 1);
        Object result = expression.execute(param);
        System.out.println(result);
    }
}
