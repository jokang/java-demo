package cn.jokang.demo.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * @author jokang
 * @date 2021/1/25
 */
public class ShareTest {
    @Test
    public void shareTest() {
        Mono<String> m1 = Mono.just("Start")
            .map(x -> {
                System.out.println("invoke map function. " + x);
                return "mapped" + x;
            }).cache();
        m1.subscribe(System.out::println);
        m1.subscribe(System.out::println);
    }
}
