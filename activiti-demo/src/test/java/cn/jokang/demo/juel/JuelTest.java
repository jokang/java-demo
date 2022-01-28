package cn.jokang.demo.juel;

import com.google.common.collect.Maps;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhoukang
 * @date 2020/11/17
 */
public class JuelTest {
    @Test
    public void simpleTest() {
        Map<String, Object> formData = Maps.newHashMap();
        formData.put("result", true);

        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        for (String k : formData.keySet()) {
            if (formData.get(k) != null) {
                ValueExpression valueExpression = factory.createValueExpression(formData.get(k),
                    formData.get(k).getClass());
                context.setVariable(k, valueExpression);
            }
        }
        final String el = "${!result}";
        ValueExpression valueExpression = factory.createValueExpression(context, el, Boolean.class);
        System.out.println(valueExpression.getValue(context));
    }
}
