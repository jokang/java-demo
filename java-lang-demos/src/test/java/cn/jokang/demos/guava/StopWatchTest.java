package cn.jokang.demos.guava;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhoukang04
 * @date 2021/7/14
 */
public class StopWatchTest {
    @Test
    public void test1() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));

        Thread.sleep(1000);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));

        stopwatch.stop();
        stopwatch.start();
        Thread.sleep(1000);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));

        stopwatch.reset();
        Thread.sleep(1000);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));

        float f = 1.163744856067064E-9F;
        System.out.println(f);

        System.out.println(String.valueOf(Long.MAX_VALUE));
    }

    @Test
    public void testDuplicate() {
        List<Long> l = Lists.newArrayList(5L, 6L, 7L, 7L);
        Map<Long, Integer> id2SlotMapRerank = l.stream().collect(Collectors.toMap(spuId -> spuId, spuId -> l.indexOf(spuId)));
    }
}
