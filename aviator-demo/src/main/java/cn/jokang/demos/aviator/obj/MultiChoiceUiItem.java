package cn.jokang.demos.aviator.obj;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorType;

import java.util.List;
import java.util.Map;

/**
 * 模拟最终生成的查询表达式
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class MultiChoiceUiItem extends AviatorObject {
    private String title;
    private String parameterName;
    private MultiChoiceLogicEnum multiChoiceLogicEnum;
    List<UiItem> uiItems;

    public MultiChoiceUiItem(MultiChoiceLogicEnum multiChoiceLogicEnum, String title, String paramName,
                             List<UiItem> items) {
        super();
        this.title = title;
        this.parameterName = paramName;
        this.uiItems = items;
        this.multiChoiceLogicEnum = multiChoiceLogicEnum;
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
            "\"logic\":\"" + multiChoiceLogicEnum + "\"" +
            ", \"title\":\"" + title + "\"" +
            ", \"parameterName\":\"" + parameterName + "\"" +
            ", \"uiItems\":" + uiItems +
            '}';
    }
}
