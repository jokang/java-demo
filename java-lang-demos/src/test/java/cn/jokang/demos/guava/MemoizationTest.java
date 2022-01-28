package cn.jokang.demos.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhoukang
 * @date 2019-06-13
 */
@Slf4j
public class MemoizationTest {
    @Test
    public void basicTest() throws InterruptedException {
        Supplier<String> memo = Suppliers.memoizeWithExpiration(() -> {
            log.info("Updating");
            return String.valueOf(System.currentTimeMillis());
        }, 3, TimeUnit.SECONDS);

        log.info(memo.get());
        Thread.sleep(1000);
        log.info(memo.get());

        Thread.sleep(3000);
        log.info(memo.get());
        log.info(memo.get());
    }
}