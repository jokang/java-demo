package cn.jokang.demos.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Strings;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoukang
 * @date 2019-11-29
 */
public class RetryWrapper {

    private static final WaitStrategy WAIT_STRATEGY = WaitStrategies.incrementingWait(100, TimeUnit.MILLISECONDS, 100
        , TimeUnit.MILLISECONDS);
    private static final StopStrategy STOP_STRATEGY = StopStrategies.stopAfterAttempt(5);

    /**
     * 如果返回为empty 或者Exception  ,就阶梯重试 步长100ms, 重试5次
     * 只比较字符串和集合类为空才会重试! 其他类型只有抛异常才重试.
     *
     * @param <T>
     * @return
     */
    public static <T> T retryIfEmptyOrExceptionIncrementWait(Callable<T> callable)
        throws ExecutionException, RetryException {
        Retryer<T> retryer = RetryerBuilder.<T>newBuilder()
            .retryIfResult(input -> {
                if (input instanceof Collection) {
                    return CollectionUtils.isEmpty((Collection) input);
                } else if (input instanceof String) {
                    return Strings.isNullOrEmpty(String.valueOf(input));
                }
                return false;
            })
            .retryIfException()
            .withWaitStrategy(WAIT_STRATEGY)
            .withStopStrategy(STOP_STRATEGY)
            .build();
        return retryer.call(callable);
    }

    /**
     * 如果返回Exception  ,就阶梯重试 步长100ms, 重试5次
     *
     * @param <T>
     * @return
     */
    public static <T> T retryIfExceptionIncrementWait(Callable<T> callable)
        throws ExecutionException, RetryException {
        Retryer<T> retryer = RetryerBuilder.<T>newBuilder().retryIfException()
            .withWaitStrategy(WAIT_STRATEGY)
            .withStopStrategy(STOP_STRATEGY)
            .build();
        return retryer.call(callable);
    }
}
