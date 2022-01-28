package cn.jokang.demo.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author jokang
 * @date 2021/1/27
 */
public class JustTest {
    @Test
    public void testJust() {
        Flux.range(1, 5)
            .map(String::valueOf)
            .subscribe(System.out::println);
    }
}
