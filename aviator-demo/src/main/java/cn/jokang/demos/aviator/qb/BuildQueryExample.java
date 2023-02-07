package cn.jokang.demos.aviator.qb;

import cn.jokang.demos.aviator.qb.obj.AndQuery;
import cn.jokang.demos.aviator.qb.obj.EqQuery;
import cn.jokang.demos.aviator.qb.obj.OrQuery;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * 自定义函数示例
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class BuildQueryExample {
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

    static class MultiAndChoiceComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject subItem1, AviatorObject subItem2) {
            EqQuery item1 = (EqQuery) FunctionUtils.getJavaObject(subItem1, env);
            EqQuery item2 = (EqQuery) FunctionUtils.getJavaObject(subItem2, env);
            return new AndQuery(item1, item2);
        }

        public String getName() {
            return "multi_and_choice";
        }

    }

    static class MultiOrChoiceComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject subItem1, AviatorObject subItem2) {
            EqQuery item1 = (EqQuery) FunctionUtils.getJavaObject(subItem1, env);
            EqQuery item2 = (EqQuery) FunctionUtils.getJavaObject(subItem2, env);
            return new OrQuery(item1, item2);
        }

        public String getName() {
            return "multi_or_choice";
        }

    }

    static class MakeItemComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject expression) {


            return expression;
        }

        public String getName() {
            return "multi_choice_item";
        }

    }


    public static void main(String[] args) {
        AviatorEvaluatorInstance queryGenerator = AviatorEvaluator.newInstance();
        queryGenerator.addFunction(new MultiAndChoiceComponent());
        queryGenerator.addFunction(new MultiOrChoiceComponent());
        queryGenerator.addFunction(new MakeItemComponent());
        queryGenerator.addOpFunction(OperatorType.EQ, new OverloadEq());
        Map<String, Object> fields = Maps.newHashMap();
        fields.put("poiStatus", "poiStatus");
        fields.put("claimStatus", "claimStatus");
        fields.put("primaryFirstTagId", "primaryFirstTagId");
        Map<String, Object> env = Maps.newHashMap();
        env.put("fields", fields);

        Map<String, Object> params = Maps.newHashMap();
        fields.put("poi_category", "poi_category_meishi,poi_category_tianpin");
        fields.put("poiStatus", "claimed");
        env.put("params", params);

        String exp1 = "multi_and_choice('商家状态', 'poiStatus', " +
            "multi_choice_item(\"已认领\", \"claimed\", fields.claimStatus==1)," +
            "multi_choice_item(\"营业中\", \"poiStatus\", fields.poiStatus==2))";
        System.out.println(queryGenerator.execute(exp1, env));

        String exp2 = "multi_or_choice('品类', 'poi_category', " +
            "multi_choice_item(\"已认领\", \"poi_category_meishi\", fields.claimStatus==1)," +
            "multi_choice_item(\"营业中\", \"poi_category_tianpin\", fields.poiStatus==2))";
        System.out.println(queryGenerator.execute(exp2, env));
        System.out.println(exp2);
    }
}
