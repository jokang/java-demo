package cn.jokang.demo.reactor.custom;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author jokang
 * @date 2021/2/8
 */
public class UserFeaturePublisher implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> s) {
        Subscription subscription = new SimpleSubscription(s);
        s.onSubscribe(subscription);
    }
}
