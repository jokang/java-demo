package cn.jokang.demos.iterate;

import org.junit.Test;

public class DataToProcessTest {
    @Test
    public void testIterate() {
        DataToProcess dp = new DataToProcess(100, 101);
        for (Poi poi : dp) {
            System.out.println(poi);
        }
    }
}