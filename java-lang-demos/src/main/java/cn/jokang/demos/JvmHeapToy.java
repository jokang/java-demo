package cn.jokang.demos;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhoukang
 * @date 2020/7/16
 */
public class JvmHeapToy {
    public static void main(String[] args) throws InterruptedException {
        List<Long> lst = Lists.newArrayList();
        for (long i = 0; i < 1000000; i++) {
            lst.add(i);
        }

        Thread.sleep(60 * 1000);
    }
}
