package cn.jokang.demos.guava;

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
public class CacheTest {
    @Test
    public void basicTest() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("k1", "v1");
        cache.put("k2", "v2");
        Assert.assertEquals("v1", cache.getIfPresent("k1"));
        Assert.assertNull(cache.getIfPresent("k3"));
    }

    @Test
    public void basicTest2() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(2)
            .expireAfterAccess(100, TimeUnit.SECONDS)
            .expireAfterWrite(100, TimeUnit.SECONDS)
            .removalListener((RemovalListener) removalNotification -> log.info(removalNotification.toString()))
            .build();
        cache.put("k1", "v1");
        cache.put("k2", "v2");
        Assert.assertEquals("v1", cache.getIfPresent("k1"));
        Assert.assertNull(cache.getIfPresent("k3"));
    }


    @Test
    public void testCallableGet() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        // 多线程访问的时候，只会调用一次callable
        cache.get("k1", () -> "v1");
        Assert.assertEquals("v1", cache.getIfPresent("k1"));
        // getIfPresent，如果没有就返回null
        Assert.assertNull(cache.getIfPresent("k3"));
    }

    @Test(expected = NullPointerException.class)
    public void testPutNullValueIntoCache() throws ExecutionException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.put(1, null);
    }

    @Test(expected = CacheLoader.InvalidCacheLoadException.class)
    public void testGetNullValueFromCache() throws ExecutionException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.get(1, () -> null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetNull() throws ExecutionException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.get(null, () -> "null");
    }

    @Test
    public void testStat() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .recordStats() //开启统计信息开关
            .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");

        log.info(cache.stats().toString()); //获取统计信息
    }

    @Test
    public void testLoadingCache() throws ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .refreshAfterWrite(30, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                public String load(String key) throws Exception {
                    Thread.sleep(1000);
                    log.info(key + " is loaded from a cacheLoader!");
                    return key + "'s value";
                }

                @Override
                public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                    ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return load(key);
                        }
                    });
                    executorService.execute(task);

                    return task;
                }
            });

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
        loadingCache.getUnchecked("key4");

        // or
        loadingCache.getUnchecked("key4");
    }

    private int throwARuntimeException() {
        throw new RuntimeException("Testing");
    }

    @Test
    public void testExpire() throws ExecutionException, InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();
        // 多线程访问的时候，只会调用一次callable
        cache.get("k1", () -> "v1");

        Thread.sleep(5000);
        System.out.println(cache.get("k1", () -> {
            throwARuntimeException();
            return "v1";
        }));
    }
}