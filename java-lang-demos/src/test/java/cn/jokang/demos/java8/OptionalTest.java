package cn.jokang.demos.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * @author zhoukang04
 * @date 2021/5/7
 */
public class OptionalTest {
    @Test
    public void testFlatMap() {
        //flatMap 主要是为了方便串起来
        System.out.println(Optional.of("a").flatMap(this::uppercase).get());
    }

    private Optional<String> uppercase(String in) {
        return Optional.ofNullable(in).map(String::toUpperCase);
    }
}
