package cn.jokang.demo.aviator.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.lexer.token.OperatorType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukang04
 * @date 2021/7/27
 */
public class AviatorEvaluationTest {
    @Test
    public void testMissingVariable() {
        // 出现未传入的变量的时候,不会抛错
        Expression expression = AviatorEvaluator.getInstance().compile("val > 'aaa'");
        Map<String, Object> param = Maps.newHashMap();
        // param.put("val", "aaa");
        Object result = expression.execute(param);
        System.out.println(result);
    }

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
    public void testTernaryOperator() {
        // 改变输入的参数
        final int num = Integer.parseInt("1");
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", num);
        String result = (String) AviatorEvaluator.execute("a>0? 'yes':'no'", env);
        System.out.println(result);
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

        // new出来的String对象和String常量能够相等,是Aviator的String做了兼容
        param = Maps.newHashMap();
        param.put("val", new String("aaa"));
        result = expression.execute(param);
        // true
        System.out.println(result);
    }

    @Test
    public void testInclude() {
        Expression expression = AviatorEvaluator.getInstance().compile("include(spu.rawCoreWords, '牙刷') ? '婴儿牙刷' : (include(spu.rawCoreWords, '牙膏') ?'婴儿牙膏':'')");
        Map<String, Object> param = Maps.newHashMap();
        param.put("spu", ImmutableMap.of("rawCoreWords", Sets.newHashSet("牙膏")));
        Object result = expression.execute(param);
        System.out.println(result);

        try {
            Object tmpReulst = AviatorEvaluator.execute("include(ids, 111)");
            System.out.println(tmpReulst);
        } catch (Exception e) {
            System.out.println(e);
        }
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

    @Test
    public void testDiv() {
        AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.newInstance();
        // ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL 只是字面值1会解析成decimal
        aviatorEvaluatorInstance.setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, true);
//        aviatorEvaluatorInstance.addOpFunction(OperatorType.DIV, );
        Expression expression = aviatorEvaluatorInstance
                .compile("(a+b+1)/c");
        Map<String, Object> param = Maps.newHashMap();
        param.put("a", 1);
        param.put("b", 2);
        param.put("c", 9);
        Object result = expression.execute(param);
        System.out.println(result);

        Expression expression2 = aviatorEvaluatorInstance
                .compile("a/c");
        Object result2 = expression2.execute(param);
        System.out.println(result2);
    }
}
