package cn.jokang.demos.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoukang
 * @date 2019-06-13
 */
@Slf4j
public class RetryTest {
    @Test
    public void test() {
        final MutableInt invokeCount = new MutableInt(0);
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder().
            retryIfResult(new Predicate<String>() {
                @Override
                public boolean apply(@Nullable String input) {
                    return Strings.isNullOrEmpty(String.valueOf(input));
                }
            })
            .retryIfException()
            .withWaitStrategy(WaitStrategies.incrementingWait(100, TimeUnit.MILLISECONDS, 100, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(5)).build();

        try {
            String result = retryer.call(() -> {
                invokeCount.add(1);
                if (invokeCount.intValue() == 4) {
                    return invokeCount.toString();
                } else {
                    throw new RuntimeException("Please wait...");
                }
            });
            log.info("result={}", result);
        } catch (ExecutionException | RetryException e) {
            log.error("重试5次之后失败");
        }
    }
}
