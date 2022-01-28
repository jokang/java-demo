package cn.jokang.demos.aviator.obj;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorType;

import java.util.Map;

/**
 * 模拟最终生成的查询表达式
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class AndQuery extends AviatorObject {
    private EqQuery arg1;
    private EqQuery arg2;

    public AndQuery(EqQuery arg1, EqQuery arg2) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
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
//        return "生成的查询条件为 ：" + arg1 + "==" + arg2;
        return this;
    }

    @Override
    public String toString() {
        return "AndQuery{" +
            "arg1='" + arg1 + '\'' +
            ", arg2='" + arg2 + '\'' +
            '}';
    }
}
