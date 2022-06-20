package cn.jokang.demos.java8;

import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhoukang04
 * @date 2022/6/13
 */
public class MyDateUtil {
    private static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String todayCompact() {
        return LocalDate.now().format(yyyyMMdd);
    }

    public static LocalDate parseDt(String dt) {
        return LocalDate.parse(dt, yyyyMMdd);
    }

    public static List<LocalDate> localDateRange(LocalDate fromDate, LocalDate toDate) {
        if (toDate.isBefore(fromDate)) {
            throw new RuntimeException("fromDate after endDate");
        }
        List<LocalDate> dates = Lists.newArrayList();
        LocalDate d = fromDate;
        while (!d.isAfter(toDate)) {
            dates.add(d);
            d = d.plusDays(1);
        }
        return dates;
    }
}
