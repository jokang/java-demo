package cn.jokang.demo.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * @author jokang
 * @date 2021/1/22
 */
public class MonoZipTest {
    @Test
    public void testZipMonos() {
        Mono<Integer> m1 = Mono.just(1);
        Mono<Integer> m2 = Mono.just(2);
        Mono.zip(x -> String.valueOf(x[0]), m1, m2)
            .subscribe(System.out::println);
    }

    @Test
    public void testDuplicate() {
        Mono<Integer> m1 = Mono.just(1);
        Mono<Integer> m2 = m1.map(m -> {
            System.out.println("work");
            return m;
        }).cache();
        m2.map(m -> m * 2).subscribe(System.out::println);
        m2.map(m -> m * 10).subscribe(System.out::println);
    }
}
