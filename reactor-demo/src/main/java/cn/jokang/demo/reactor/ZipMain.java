package cn.jokang.demo.reactor;

import reactor.core.publisher.Mono;

public class ZipMain {
    public static void main(String[] args) throws InterruptedException {
        Mono.just(1L).zipWith(Mono.just("haha")).map(x -> x.getT1() + x.getT2()).subscribe(System.out::println);

    }
}
