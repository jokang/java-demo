package cn.jokang.demos.concurrent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author zhoukang04
 * @date 2022/1/28
 */
public class TimerTests {
    /**
     * 基本用法
     */
    @Test
    public void runWithTimer() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("run it.");
            }
        }, 3000L);
        Thread.sleep(5000L);
    }

    /**
     * Timer对象被回收,但TimerThread对象依旧会运行.
     */
    @Test
    public void autoDestroyTimerThread() throws InterruptedException {
        t();
        //
        System.gc();
        Thread.sleep(10000L);
    }

    private void t() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("run it.");
            }
        }, 2000L, 1000L);
    }

    /**
     * Timer的问题:
     * Timer中的任务如果抛异常,后面的任务都没机会运行.
     * 一个线程运行任务,前面阻塞后面的
     * 基于绝对时间调度,对系统时间敏感.
     * <p>
     * 使用ScheduledExecutorService会好点儿. 使用BlockingQueue管理任务队列. Timer使用TaskQueue管理,实际上是一个小顶堆.用Object的wait,notify机制实现阻塞.
     */
    @Test
    public void testSchedule() {
        ScheduledExecutorService scheduledThreadPool = null;
        try {
            scheduledThreadPool = Executors.newScheduledThreadPool(4);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            System.out.println("submit a callable task to threadpool, current time:" + sdf.format(new Date()));
            ScheduledFuture<String> scheduledFuture = scheduledThreadPool.schedule(() -> {
                System.out.println("start to execute task, current time:" + sdf.format(new Date()));
                TimeUnit.SECONDS.sleep(3L);
                return "success";
            }, 2L, TimeUnit.SECONDS);
            System.out.println("result:" + scheduledFuture.get() + ", current time:" + sdf.format(new Date()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (scheduledThreadPool != null) {
                System.out.println("close the schedule threadpool!");
                scheduledThreadPool.shutdown();
            }
        }
    }
}
