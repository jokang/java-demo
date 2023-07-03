package cn.jokang.demos.df;

import org.junit.Test;
import tech.tablesaw.api.DoubleColumn;

/**
 * <a href="https://jtablesaw.github.io/tablesaw/gettingstarted.html">https://jtablesaw.github.io/tablesaw/gettingstarted.html</a>
 */
public class TableSawTest {
    @Test
    public void testCreateColumn() {
        double[] numbers = {1, 2, 3, 4};
        DoubleColumn nc = DoubleColumn.create("nc", numbers);
        System.out.println(nc.print());
    }
}
