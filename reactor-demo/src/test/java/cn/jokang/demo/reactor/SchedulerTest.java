package cn.jokang.demo.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author jokang
 * @date 2021/1/26
 */
public class SchedulerTest {
    @Test
    public void testSubscribeOn() {
        Scheduler s = Schedulers.newParallel("subsribe-scheduler", 4);
        Scheduler p = Schedulers.newParallel("publish-scheduler", 4);
        final Flux<String> flux = Flux
            .range(1, 2)
            .map(i -> {
                System.out.println("map1 on:" + Thread.currentThread().getName());
                return 10 + i;
            })
            .publishOn(p)
            .subscribeOn(s)
            .map(i -> {
                System.out.println("map2 on:" + Thread.currentThread().getName());
                return "value " + i;
            })
            .checkpoint("Firt checkpoint")
            .log();
        flux.subscribe(x -> System.out.println("subscribe:" + Thread.currentThread().getName()));
    }
}
