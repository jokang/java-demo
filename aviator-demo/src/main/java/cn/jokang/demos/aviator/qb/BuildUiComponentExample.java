package cn.jokang.demos.aviator.qb;

import cn.jokang.demos.aviator.qb.obj.MultiChoiceLogicEnum;
import cn.jokang.demos.aviator.qb.obj.MultiChoiceUiItem;
import cn.jokang.demos.aviator.qb.obj.UiItem;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
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
public class BuildUiComponentExample {
    static class MultiAndChoiceComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject subItem1, AviatorObject subItem2) {
            String titleStr = FunctionUtils.getStringValue(title, env);
            String paramNameStr = FunctionUtils.getStringValue(paramName, env);
            UiItem item1 = (UiItem) FunctionUtils.getJavaObject(subItem1, env);
            UiItem item2 = (UiItem) FunctionUtils.getJavaObject(subItem2, env);
            return new MultiChoiceUiItem(MultiChoiceLogicEnum.AND, titleStr, paramNameStr, Lists.newArrayList(item1,
                item2));
        }


        @Override
        public String getName() {
            return "multi_and_choice";
        }

    }

    static class MultiOrChoiceComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject subItem1, AviatorObject subItem2) {
            String titleStr = FunctionUtils.getStringValue(title, env);
            String paramNameStr = FunctionUtils.getStringValue(paramName, env);
            UiItem item1 = (UiItem) FunctionUtils.getJavaObject(subItem1, env);
            UiItem item2 = (UiItem) FunctionUtils.getJavaObject(subItem2, env);
            return new MultiChoiceUiItem(MultiChoiceLogicEnum.OR, titleStr, paramNameStr, Lists.newArrayList(item1,
                item2));
        }


        @Override
        public String getName() {
            return "multi_or_choice";
        }

    }

    static class MakeItemComponent extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject title, AviatorObject paramName,
                                  AviatorObject expression) {
            String titleStr = FunctionUtils.getStringValue(title, env);
            String paramNameStr = FunctionUtils.getStringValue(paramName, env);
            return new UiItem(titleStr, paramNameStr);
        }


        @Override
        public String getName() {
            return "multi_choice_item";
        }

    }


    public static void main(String[] args) {
        AviatorEvaluatorInstance uiGeneratorInstance = AviatorEvaluator.newInstance();
        uiGeneratorInstance.addFunction(new MultiAndChoiceComponent());
        uiGeneratorInstance.addFunction(new MultiOrChoiceComponent());
        uiGeneratorInstance.addFunction(new MakeItemComponent());
        Map<String, Object> fields = Maps.newHashMap();
        fields.put("poiStatus", 1);
        fields.put("poiOnline", 2);
        fields.put("primaryFirstTagId", 19);
        Map<String, Object> env = Maps.newHashMap();
        env.put("fields", fields);
        String exp1 = "multi_and_choice('商家状态', 'poiStatus', " +
            "multi_choice_item(\"已认领\", \"claimStatus\", fields.claimStatus==1)," +
            "multi_choice_item(\"营业中\", \"poiStatus\", fields.poiStatus==2))";
        System.out.println(uiGeneratorInstance.execute(exp1, env));

        String exp2 = "multi_or_choice('品类', 'poi_category', " +
            "multi_choice_item(\"已认领\", \"poi_category_meishi\", fields.claimStatus==1)," +
            "multi_choice_item(\"营业中\", \"poi_category_tianpin\", fields.poiStatus==2))";
        System.out.println(exp1);
        System.out.println(uiGeneratorInstance.execute(exp2, env));
    }
}
