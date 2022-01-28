package cn.jokang.demos.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Guava LoadingCache的封装
 * @author zhoukang
 * @date 2019-11-28
 */
public class LoadingCacheTool {
    public static <K, V> LoadingCache create(ExecutorService executorService, Function<K, V> loadMethod) {
        LoadingCache<K, V> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .refreshAfterWrite(30, TimeUnit.SECONDS)
            .build(new CacheLoader<K, V>() {
                @Override
                public V load(K key) throws Exception {
                    return loadMethod.apply(key);
                }

                @Override
                public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
                    ListenableFutureTask<V> task = ListenableFutureTask.create(new Callable<V>() {
                        @Override
                        public V call() throws Exception {
                            return load(key);
                        }
                    });
                    executorService.execute(task);

                    return task;
                }
            });
        return loadingCache;
    }

    public static void main(String[] args) throws ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        LoadingCache<String, String> loadingCache = LoadingCacheTool.create(executorService, x -> x);
        System.out.println(loadingCache.get("a"));
        System.out.println(loadingCache.get("b"));

    }
}
