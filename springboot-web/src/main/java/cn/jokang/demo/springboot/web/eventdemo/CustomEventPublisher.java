package cn.jokang.demo.springboot.web.eventdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author jokang
 * @date 2020/11/26
 */
@Component
public class CustomEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(String msg) {
        publisher.publishEvent(new CustomEvent(msg));
    }
}
