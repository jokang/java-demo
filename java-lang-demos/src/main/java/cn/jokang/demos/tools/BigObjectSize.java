package cn.jokang.demos.tools;

/**
 * @author zhoukang
 * @date 2020-02-18
 */

import com.google.common.collect.Lists;

import java.util.List;

public class BigObjectSize {
    public static void main(String[] args) {
        List<Long> lists = Lists.newArrayList();
        for (long i = 0; i < 1000000; i++) {
            lists.add(i);
        }
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}