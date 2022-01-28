package cn.jokang.demo.springboot.web.eventdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jokang
 * @date 2020/11/26
 */
@Configuration
@EnableAsync
public class EventConfig {
    /**
     * 自定义异步线程池
     *
     * @return
     */
//    @Bean(name = "defaultThreadPool")
//    public AsyncTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("default-Executor");
//        executor.setMaxPoolSize(10);
//
//        // 设置拒绝策略
//        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                // .....
//            }
//        });
//        // 使用预定义的异常处理类
//        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//
//        return executor;
//    }

    @Bean(name = "customThreadPool")
    public AsyncTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("customThreadPool-Executor");
        executor.setMaxPoolSize(10);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // .....
            }
        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }
}
