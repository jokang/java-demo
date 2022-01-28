package cn.jokang.demos.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import java.util.concurrent.TimeUnit;

/**
 * Suppliers可以用来缓存单个方法的返回值,但是不支持异步刷新缓存.
 *
 * @author zhoukang
 * @date 2019-11-29
 */
public class SupplierTool {
    public static <T> T call(Supplier<T> supplier) {
        return Suppliers.memoizeWithExpiration(supplier, 1, TimeUnit.DAYS).get();
    }
}
