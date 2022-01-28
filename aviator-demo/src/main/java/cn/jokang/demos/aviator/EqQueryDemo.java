package cn.jokang.demos.aviator;

import cn.jokang.demos.aviator.obj.EqQuery;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * 演示重载相等操作符，生成相等查询。
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class EqQueryDemo {
    public static class OverloadEq extends AbstractFunction {

        @Override
        public String getName() {
            return "==";
        }

        @Override
        public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1,
                                  final AviatorObject arg2) {

            String arg1Str = String.valueOf(arg1.getValue(env));
            String arg2Str = String.valueOf(arg2.getValue(env));
            return new EqQuery(arg1Str, arg2Str);
        }

    }

    public static void main(final String[] args) {
        AviatorEvaluator.addOpFunction(OperatorType.EQ, new OverloadEq());
        Map<String, Object> env = Maps.newHashMap();
        env.put("labelKey", "labelKeyValue");
        System.out.println(AviatorEvaluator.execute("labelKey==1", env));
    }
}
