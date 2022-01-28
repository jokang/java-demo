package cn.jokang.demo.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @author zhoukang
 * @date 2021/1/15
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {
    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
