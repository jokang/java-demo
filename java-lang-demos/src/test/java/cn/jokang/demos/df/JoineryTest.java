package cn.jokang.demos.df;

import joinery.DataFrame;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <a href="https://github.com/cardillo/joinery">https://github.com/cardillo/joinery</a>
 */
public class JoineryTest {
    @Test
    public void test() throws IOException {
        List<Integer> col = DataFrame.readCsv(ClassLoader.getSystemResourceAsStream("joinery_demo.csv"))
                .retain("Date", "Close")
                .groupBy(row -> Date.class.cast(row.get(0)).getMonth())
                .mean()
                .sortBy("Close")
                .tail(3)
                .apply(value -> Number.class.cast(value).intValue())
                .col("Close");
        System.out.println(col);
    }
}
