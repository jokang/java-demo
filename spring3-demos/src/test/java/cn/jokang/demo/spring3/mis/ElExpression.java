package cn.jokang.demo.spring3.mis;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


/**
 * Created by zhoukang on 18/12/12.
 */
public class ElExpression {
	@Test
	public void testElExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("T(java.lang.Integer).MAX_VALUE");
		Object message = exp.getValue();
		System.out.println(message);
	}
}
