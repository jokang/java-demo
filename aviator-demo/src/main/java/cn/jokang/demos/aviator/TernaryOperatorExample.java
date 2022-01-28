package cn.jokang.demos.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * 三元操作符示例
 *
 * @author zhoukang
 * @date 2019-08-11
 */
public class TernaryOperatorExample {
    public static void main(String[] args) {
        // 改变输入的参数
        final int num = Integer.parseInt("1");
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", num);
        String result = (String) AviatorEvaluator.execute("a>0? 'yes':'no'", env);
        System.out.println(result);
    }
}
