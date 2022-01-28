package cn.jokang.demo.reactor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhoukang
 * @date 2021/1/15
 */
public class FluxMain {
    public static void main(String[] args) throws InterruptedException {
//        Flux<Integer> ints = Flux.range(1, 4);
//        Flux<Integer> filterIntes = ints.filter(x -> x < 3);
//        Flux<String> strings = filterIntes.map(x -> String.valueOf(x));
//        strings.subscribe(System.out::println);
//        Flux<Integer> ints = Flux.range(1, 3)
//            .map(i -> {
//                if (i == 2) throw new IllegalStateException("");
//                return i;
//            })
//            .onErrorReturn(5);
//        ints.subscribe(System.out::println);


//

//
//            .map(x -> x);Observable
//
//        new Thread(() -> flux.subscribe(x -> System.out.println("subscribe:" + Thread.currentThread().getName()))).start();
//        Thread.sleep(20*1000);
//        Flux<Integer> ints = Flux.range(1, 3)
//            .map(i -> {
//                if (i <=3) return i;
//                throw new RuntimeException("Got to 4");
//            });("h
//
//        Disposable disposable = ints.subscribe(System.out::println, error -> System.out.println("Error: " + error), () -> System.out.println("Done"),
//            sub -> sub.request(2));
//        disposable.dispose();

//        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
//        Flux<Integer> ints = Flux.range(1, 4);
//        ints.buffer(2);
//        ints.subscribe(ss);

//        Flux.error(new IllegalArgumentException());
//        Schedulers.single()

//        Recaller r  = new MockRecaller();
//
//        List<Integer> ins = Lists.newArrayList(1, 2, 3);
//        ins.stream().collect(Collectors.toList());

    }
}
