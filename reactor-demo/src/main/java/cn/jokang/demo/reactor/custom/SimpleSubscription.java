package cn.jokang.demo.reactor.custom;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author jokang
 * @date 2021/2/8
 */
public class SimpleSubscription implements Subscription {
    private final Subscriber s;
    public SimpleSubscription(Subscriber s) {
        this.s = s;
    }

    @Override
    public void request(long n) {
        for (int i = 0; i<n; i++) {
            s.onNext(i);
        }
        s.onComplete();
    }

    @Override
    public void cancel() {

    }
}
