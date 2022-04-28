package cn.jokang.demos.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhoukang04
 * @date 2021/5/13
 */
public class CompletableFutureTest {
    @Test
    public void testForDebug() {
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> 1);
        stringCompletableFuture.thenApply(x -> x + 1).thenAccept(System.out::println);
    }

    // 测试直接设置值的, 后续步骤可以触发
    @Test
    public void testValue() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("Done");
        stringCompletableFuture.thenApply(s -> s + " transform").thenAccept(System.out::println);
    }

    // 直接提供值的CompletableFuture, get的时候可以不用指定timeout
    @Test
    public void testValueTimeOut() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("Done");
        System.out.println(stringCompletableFuture.get());
    }

    @Test
    public void testReuse() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("Done");
        stringCompletableFuture.thenApply(s -> s + " transformAAA").thenAccept(System.out::println);
        stringCompletableFuture.thenApply(s -> s + " transformBBB").thenAccept(System.out::println);
    }

    // 返回的null可以往下传递
    @Test
    public void testNull() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> null);
        stringCompletableFuture.thenAccept(System.out::println);
    }

    // thenApply在哪个线程里面运行?
    // 1)如果调用a.thenApply的时候,还没有complete, 在a的线程池里运行.
    // 2)如果已经complete, 在当前线程运行
    // https://stackoverflow.com/questions/46060438/in-which-thread-does-completablefutures-completion-handlers-execute-in
    @Test
    public void testThenApply1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("threadName: " + Thread.currentThread().getName());
            try {
                // 设置一个比较长的时间, 调用thenApply的时候, 这一步还没有跑完
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Value";
        });
        step1.thenApply(s -> {
            // 在step1的线程中运行
            System.out.println("threadName: " + Thread.currentThread().getName());
            return s + " transformAAA";
        }).get();
    }

    @Test
    public void testThenApply2() throws InterruptedException, ExecutionException {
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("threadName: " + Thread.currentThread().getName());
            // 运行时间比较短, step1.thenApply的时候已经结束
            return "Value";
        });
        step1.thenApply(s -> {
            // 在当前线程中运行
            System.out.println("threadName: " + Thread.currentThread().getName());
            return s + " transformAAA";
        }).get();
    }

    // thenCompose和thenApply的区别? 主要是为了方便串一个输出是CompletableFuture的函数
    // thenCompose() uses the previous stage as the argument
    @Test
    public void testThenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> step1 = CompletableFuture.completedFuture("A");
        step1.thenCompose(s -> CompletableFuture.completedFuture(s + "B")).get();
    }

    // 结合另外一个CompletableFuture的结果, 转换成另外一个
    @Test
    public void testThenCombine() throws InterruptedException, ExecutionException {
        CompletableFuture<String> step1 = CompletableFuture.completedFuture("A");
        CompletableFuture<Integer> step2 = CompletableFuture.completedFuture(2);
        System.out.println(step1.thenCombine(step2, (s1, s2) -> s1 + s2).get());
    }
}
