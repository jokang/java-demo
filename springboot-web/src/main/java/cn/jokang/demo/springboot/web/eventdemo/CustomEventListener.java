package cn.jokang.demo.springboot.web.eventdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author jokang
 * @date 2020/11/26
 */
@Component
public class CustomEventListener {
    @EventListener
    public void processCustomEvent(CustomEvent event) {
        System.out.println(Thread.currentThread().getName() + ":" + event.getMsg());
    }

    @EventListener
    @Async
    public void processCustomEventAsync2(CustomEvent event) {
        System.out.println(Thread.currentThread().getName() + ":" + "Async:" + event.getMsg());
    }

    @EventListener
    // 指定线程池
    // 如果不指定且未配置线程池,则使用SimpleAsyncTaskExecutor
    // 如果不指定且配置了一个线程池,则使用配置的
    // 如果不指定且配置了多个线程池,则使用SimpleAsyncTaskExecutor
    @Async("customThreadPool")
    public void processCustomEventAsync(CustomEvent event) {
        System.out.println(Thread.currentThread().getName() + ":" + "Async:" + event.getMsg());
    }


}
