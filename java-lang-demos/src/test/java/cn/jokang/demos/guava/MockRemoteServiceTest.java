package cn.jokang.demos.guava;

import cn.jokang.demos.mock.MockRemoteService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MockRemoteServiceTest {
    MockRemoteService mockRemoteService;
    @Before
    public void init() {
        mockRemoteService = new MockRemoteService();
    }

    @Test
    public void testConsumer() throws Exception {
        CompletableFuture<Void> c = CompletableFuture.supplyAsync(() -> mockRemoteService.processing("p"))
            .thenAccept(System.out::println);
        c.join();
    }

    @Test
    public void test() throws Exception {
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> mockRemoteService.processing("p"));
        c.thenApply(x -> "Mapped " + x)
        .thenAccept(System.out::println);
        c.join();
    }

    @Test
    public void test1() {
        MockRemoteService mockRemoteService = new MockRemoteService();
        List<String> lst = Lists.newArrayList("A", "B", "C");
        // 一个一个请求
        /*lst.stream()
            .forEach(x -> {
                try {
                    concurretTool.processing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });*/

        // 改成并发流,计算密集型适合这种
        /*lst.parallelStream()
            .forEach(x -> {
                try {
                    concurretTool.processing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });*/

        // 这样还是同步的!!!
        /*List<String> ss = lst.stream()
            .map(x -> CompletableFuture.supplyAsync(() -> {
                try {
                    return concurretTool.processing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }))
            .map(x -> x.join())
            .collect(Collectors.toList());
        System.out.println(ss);*/
        //分开两个stream才对
//        List<CompletableFuture<String>> completableFutures = lst.stream()
//            .map(x -> CompletableFuture.supplyAsync(() -> {
//                try {
//                    return mockRemoteService.processing();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }))
//            .collect(Collectors.toList());
//
//        // join不会抛异常,get会
//        List<String> strs = completableFutures.stream()
//            .map(x -> x.join())
//            .collect(Collectors.toList());
//        System.out.println(strs);

    }

}