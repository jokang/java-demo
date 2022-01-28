package cn.jokang.demos.lang;

import org.junit.Test;

/**
 * @author zhoukang
 * @date 2020/7/12
 */
public class VariableParamTest {
    private void methodTakeVariables(String ... args) {
        System.out.println(args);
    }

    @Test
    public void passNull() {
        methodTakeVariables(null);
    }

    @Test
    public void passNothing() {
        methodTakeVariables();
    }

}
