package cn.jokang.demos.concurrent;

import cn.jokang.demos.mock.MockRemoteService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author jokang
 * @date 2019-06-15
 */
@Slf4j
public class MyExecutorTest {

    static class SimpleRunnable implements Runnable {
        private int index;

        public SimpleRunnable(int i) {
            this.index = i;
        }

        @Override
        public void run() {
            try {
                log.info("[" + this.index + "] start....");
                Thread.sleep((int) (Math.random() * 1000));
                log.info("[" + this.index + "] end.");
            } catch (Exception e) {
                log.error("Fail", e);
            }
        }
    }

    static class SimpleCallable implements Callable<Integer> {
        private int index;

        public SimpleCallable(int i) {
            this.index = i;
        }

        @Override
        public Integer call() {
            try {
                log.info("[" + this.index + "] start....");
                Thread.sleep((int) (Math.random() * 1000));
                log.info("[" + this.index + "] end.");
            } catch (Exception e) {
                log.error("Fail", e);
            }
            return index;
        }
    }


    @Test
    public void testExecute() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            service.execute(new SimpleRunnable(i));
        }
        log.info("execute finish");
        service.shutdown();
    }

    @Test
    public void testSubmit() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            futures.add(service.submit(new SimpleCallable(i)));

            Integer expected = 1;
            futures.add(service.submit(new SimpleRunnable(i), expected));
        }
        log.info("submit finish");
        //service.shutdown();
        service.awaitTermination(10L, TimeUnit.SECONDS);
    }

    @Test
    public void testCancel() throws InterruptedException {
        MockRemoteService mockRemoteService = new MockRemoteService();
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future<String> f = service.submit(() -> mockRemoteService.processing("P1"));
        Thread.sleep(1000);
        f.cancel(true);
        log.info("submit finish");
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
    }

}
