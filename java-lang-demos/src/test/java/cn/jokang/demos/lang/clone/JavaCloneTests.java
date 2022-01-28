package cn.jokang.demos.lang.clone;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

/**
 * @author jokang
 * @date 2021/3/25
 */
@Slf4j
public class JavaCloneTests {
    @Test
    public void test() throws Exception {
        Person p = new Person(10, "zhangsan");
        Person cloned = ObjectUtils.clone(p);
        p.setAge(100);
        p.setName("lisi");
        System.out.println(p);
        System.out.println(cloned);
    }
}
