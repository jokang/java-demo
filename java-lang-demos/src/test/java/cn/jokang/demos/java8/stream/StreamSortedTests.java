package cn.jokang.demos.java8.stream;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhoukang
 * @date 2019-03-31
 */
@Slf4j
public class StreamSortedTests {
    @Test
    public void testSortString() {
        List<String> ss = Lists.newArrayList("L5", "L2", "L3", "L4", "L1");
        log.info("{}", ss.stream().sorted(Comparator.comparing(Function.identity())).collect(Collectors.toList()));
    }

    @Test
    public void testSortDate() {
        List<Date> dates = Lists.newArrayList(new Date(1000), new Date(2000), new Date(3000), null);
        // 倒序排
        List<Date> sortedDates = dates.stream()
            .sorted(Comparator.comparing(x -> null == x ? null : x.getTime(),
                Comparator.nullsFirst(Comparator.reverseOrder())))
            .collect(Collectors.toList());
        log.info("{}", sortedDates);
    }

    @Test
    public void testSortedByDateTimeString() {
        List<String> dateTimeString = Lists.newArrayList("2019-01-01 00:00:00",
            "2018-01-01 00:00:00");
        List<String> sortedList = dateTimeString.stream()
            .sorted(Comparator.comparing(String::toString).reversed())
            .collect(Collectors.toList());
        log.info("sortedList={}", sortedList);

        sortedList = dateTimeString.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        log.info("sortedList={}", sortedList);
    }

    @Test
    public void testMaxOfLongList() {
        List<Long> lst = Lists.newArrayList(1L, 2L, 3L, 4L);
        Long max = lst.stream()
            .max(Long::compare)
            .orElse(null);
        log.info("Max of lst is {}", max);
    }

    @Test
    public void testSortMaxAbx() {
        List<Pair<String, Integer>> data = Lists.newArrayList();
        data.add(Pair.of("k1", 1));
        data.add(Pair.of("k2", 2));
        data.add(Pair.of("k3", 3));
        data.add(Pair.of("k4", 4));
        data.add(Pair.of("k5", 5));

        List<Pair<String, Integer>> sorted = data.stream().sorted(Comparator.comparing(x -> {
            int abs = Math.abs(x.getRight() - 3);
//            System.out.println(abs);
            return abs;

        }))
//            .peek(x -> System.out.println(x))
            .limit(3).collect(Collectors.toList());

        System.out.println(sorted);
    }

    @Test
    public void testSortDouble() {
        List<Double> data = Lists.newArrayList(1D, 2D, 3D, 4D);
        System.out.println(data.stream()
            .sorted(Comparator.<Double>comparingDouble(x -> x).reversed())
            .limit(1)
            .collect(Collectors.toList()));
    }
}
