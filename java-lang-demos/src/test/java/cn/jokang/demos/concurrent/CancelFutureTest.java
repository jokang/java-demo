package cn.jokang.demos.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhoukang04
 * @date 2021/5/14
 */
public class CancelFutureTest {
    @Test
    public void testCancel1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> f = executorService.submit(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    i++;
                    System.out.println("Loop:" + i);
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        });
        Thread.sleep(5000L);
        // 不会中断
        boolean cancelResult = f.cancel(false);
        System.out.println("Future.cancel(false) returns: " + cancelResult);
        Thread.sleep(60000L);
    }

    @Test
    public void testCancel2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> f = executorService.submit(() -> {
            int i = 0;
            while (i < 10) {
                try {
                    i++;
                    System.out.println("Loop:" + i);
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        });
        Thread.sleep(5000L);
        // Thread.sleep会抛中断异常
        boolean cancelResult = f.cancel(true);
        System.out.println("Future.cancel(false) returns: " + cancelResult);
        Thread.sleep(60000L);
    }

    @Test
    public void testCancel3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> f = executorService.submit(() -> {
            int i = 0;
            // 响应中断
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println("Loop:" + i);
            }
            System.out.println("Done");
            return "Done";
        });
        Thread.sleep(5000L);
        // Thread.sleep会抛中断异常
        boolean cancelResult = f.cancel(true);
        System.out.println("Future.cancel(false) returns: " + cancelResult);
        Thread.sleep(60000L);
    }
}
