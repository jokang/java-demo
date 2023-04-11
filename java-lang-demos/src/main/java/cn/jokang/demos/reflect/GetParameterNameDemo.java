package cn.jokang.demos.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用反射获取参数名，需要编译器加上 -parameters 参数，或者改maven编译配置。
 * https://stackoverflow.com/questions/2237803/can-i-obtain-method-parameter-name-using-java-reflection
 *
 * @author zhoukang04
 * @date 2023/2/8
 */
public class GetParameterNameDemo {
    public static List<String> getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> parameterNames = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if (!parameter.isNamePresent()) {
                throw new IllegalArgumentException("Parameter names are not present!");
            }

            String parameterName = parameter.getName();
            parameterNames.add(parameterName);
        }

        return parameterNames;
    }


    public static void main(String[] args) {
        Method[] declaredMethods = UselessClass.class.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals("aMethod")) {
                System.out.println(getParameterNames(declaredMethod));
                break;
            }
        }
    }
}
