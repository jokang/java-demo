package cn.jokang.demos.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhoukang
 * @date 2020/11/15
 */
public class ThreadFactoryBuilderDemo {
    private static final ExecutorService executorService = new ThreadPoolExecutor(0, 3,
        3000L, TimeUnit.MILLISECONDS,
//        new LinkedBlockingQueue<>(),
        new SynchronousQueue<>(),
        new ThreadFactoryBuilder().setNameFormat("mythreads-%d")
            // 设置这个,不阻塞JVM退出
            .setDaemon(true)
            .build(),
        new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        Callable<String> r = () -> {
            try {
                System.out.println("Start to sleep.");
                Thread.sleep(10000);
                System.out.println("Finish sleeping.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        };
        List<Callable<String>> runnableList = Lists.newArrayList(r, r, r);
        executorService.invokeAll(runnableList);
    }
}
