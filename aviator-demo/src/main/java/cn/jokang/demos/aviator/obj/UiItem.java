package cn.jokang.demos.aviator.obj;

import com.googlecode.aviator.runtime.type.AviatorJavaType;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorType;

import java.util.Map;

/**
 * 模拟最终生成的查询表达式
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class UiItem extends AviatorJavaType {
    private String title;
    private String parameterName;

    public UiItem(String title, String parameterName) {
        super("UiItem");
        this.title = title;
        this.parameterName = parameterName;
    }

    @Override
    public int compare(AviatorObject other, Map<String, Object> env) {
        return 0;
    }

    @Override
    public AviatorType getAviatorType() {
        return AviatorType.JavaType;
    }

    @Override
    public Object getValue(Map<String, Object> env) {
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            "\"title\":\"" + title + "\"" +
            ", \"parameterName\":\"" + parameterName + "\"" +
            '}';
    }
}
