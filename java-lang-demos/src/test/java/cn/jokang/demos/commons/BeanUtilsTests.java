package cn.jokang.demos.commons;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTests {
	public static class MyObject {
		private String normalField;
		private String snake_case;
		private boolean primaryBoolean;

		public boolean isPrimaryBoolean() {
			return primaryBoolean;
		}

		public void setPrimaryBoolean(boolean primaryBoolean) {
			this.primaryBoolean = primaryBoolean;
		}

		public Boolean getWrappedBoolean() {
			return wrappedBoolean;
		}

		public void setWrappedBoolean(Boolean wrappedBoolean) {
			this.wrappedBoolean = wrappedBoolean;
		}

		private Boolean wrappedBoolean;

		public String getNormalField() {
			return normalField;
		}

		public void setNormalField(String normalField) {
			this.normalField = normalField;
		}

		public String getSnake_case() {
			return snake_case;
		}

		public void setSnake_case(String snake_case) {
			this.snake_case = snake_case;
		}

		@Override
		public String toString() {
			return "MyObject{" +
					"normalField='" + normalField + '\'' +
					", snake_case='" + snake_case + '\'' +
					'}';
		}
	}
	@Test
	public void testBeanUtilsMap2Bean() throws InvocationTargetException, IllegalAccessException {
		Map<String, Object> m = new HashMap<>();
		m.put("normal_field", "This is the normalField.");
		m.put("snake_case", "This is the snake_case");
		MyObject o = new MyObject();
		BeanUtils.populate( o, m);
		System.out.println(o);
	}
}
