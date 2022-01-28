package cn.jokang.demos.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhoukang
 * @date 2019-10-13
 */
public class RateLimiterTest {

    @Test
    public void test() {
        RateLimiter rl = RateLimiter.create(0.5);
        for (int i = 0; i < 4; i++) {
            rl.acquire();
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        LocalDate startDateForQuery;
        LocalDate endDateForQuery;
        LocalDate now = LocalDate.now();

        startDateForQuery = now.minusDays(1);
        endDateForQuery = now.minusDays(1);

        List<String> dtRange = Lists.newArrayList();
        LocalDate current = startDateForQuery;
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        while (!current.isAfter(endDateForQuery)) {
            dtRange.add(dtf2.format(current));
            current = current.plusDays(1);
        }

        System.out.println(dtRange);
    }
}
