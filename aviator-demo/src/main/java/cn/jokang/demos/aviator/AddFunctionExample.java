package cn.jokang.demos.aviator;

import cn.jokang.demos.aviator.obj.EqQuery;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorNil;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 自定义函数示例
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class AddFunctionExample {
    static class ParameterContainsFunction extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
            String arg1String = FunctionUtils.getStringValue(arg1, env);
            return StringUtils.isNotEmpty(arg1String)? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
        }


        public String getName() {
            return "param_contains";
        }

    }

    static class ShowParamFunction extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
            Object o = arg1.getValue(env);
            System.out.println(o);
            return AviatorNil.NIL;
        }


        public String getName() {
            return "show_param";
        }

    }

    public static class OverloadEq extends AbstractFunction {

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

    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new ParameterContainsFunction());
        AviatorEvaluator.addFunction(new ShowParamFunction());
        AviatorEvaluator.addOpFunction(OperatorType.EQ, new OverloadEq());
        Map<String, Object> env = Maps.newHashMap();
        List<Integer> lstParam = Lists.newArrayList(1, 2, 3);
        env.put("keyword", "xxx");
        env.put("labelKey", "labelKeyValue");
        env.put("lstParam", lstParam);
//        System.out.println(AviatorEvaluator.execute("param_contains(keyword) ? keyword==keyword : nil", env));
//        System.out.println(AviatorEvaluator.execute("param_contains(keyword) ? keyword==keyword : nil"));
//        System.out.println(AviatorEvaluator.execute("1+1;2+3"));
        System.out.println(AviatorEvaluator.execute("show_param(lstParam)", env));

    }
}
