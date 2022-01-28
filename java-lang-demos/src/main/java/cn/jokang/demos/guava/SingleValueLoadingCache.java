package cn.jokang.demos.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Guava LoadingCache的封装
 *
 * @author zhoukang
 * @date 2019-11-28
 */
public class SingleValueLoadingCache<V> {
    private static final String KEY = "K";

    private LoadingCache<String, V> loadingCache;

    public SingleValueLoadingCache(ExecutorService executorService, Supplier<V> loadMethod) throws ExecutionException {
        LoadingCache<String, V> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .refreshAfterWrite(30, TimeUnit.SECONDS)
            .build(new CacheLoader<String, V>() {
                @Override
                public V load(String key) throws Exception {
                    return loadMethod.get();
                }

                @Override
                public ListenableFuture<V> reload(String key, V oldValue) throws Exception {
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

        loadingCache.get(KEY);
    }

    public V get() throws ExecutionException {
        return loadingCache.get(KEY);
    }
}
