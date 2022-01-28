package cn.jokang.demos.lang.generictypes;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhoukang
 * @date 2019-03-27
 */
@Slf4j
public class WildcardGenericTypeTest {

    @Test
    public void wildcardTest() {
        List<String> lst1 = Lists.newArrayList("1", "2");
        someMethod(lst1);

        List<String> lst2 = Lists.newArrayList("1", "2");
        List<List<String>> lst = Lists.newArrayList(lst1, lst2);
        // 不能调用：
        //someMethod2(lst);
        someMethod2(Lists.newArrayList(lst));
        //这个可以调用！！！
        someMethod3(lst);
    }

    private void someMethod(List<?> lst) {
        System.out.println(lst);
    }

    private void someMethod2(List<List<?>> lst) {
        // 这个参数声明, 外层的List的类型参数是List<?>
        // List<List<String>>的外层List的类型参数是List<String>，所以不匹配。
        System.out.println(lst);
    }

    private void someMethod3(List<? extends List<?>> lst) {
        // 这个参数声明, 外层的List的类型参数是? extends List<?>
        // List<List<String>>的外层List的类型参数是List<String>，是List<?>的子类型，所以能匹配上。
        System.out.println(lst);
    }

    @Test
    public void testObjectGetClass() {
        // 演示了子类如何获得泛型参数
        List<String> lst = new ArrayList<String>(){};
        log.info(lst.getClass().getName());
//        for (Type t : String.class.getClass().getGenericInterfaces()) {
//            log.info(String.valueOf(t));
//        }
        log.info("getGenericSuperclass=" + lst.getClass().getGenericSuperclass());

        Type genType = lst.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        log.info(params[0].toString());
    }
}
