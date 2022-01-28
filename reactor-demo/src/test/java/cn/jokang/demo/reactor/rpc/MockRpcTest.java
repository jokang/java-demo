package cn.jokang.demo.reactor.rpc;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jokang
 * @date 2021/1/29
 */
public class MockRpcTest {
    private MockBizService mockBizService;

    @Before
    public void setup() {
        mockBizService = new MockBizService();
    }

    @Test
    public void testCall() throws InterruptedException {
        long start = System.currentTimeMillis();
        mockBizService.recommend(1)
            .subscribe(x -> {
                long end = System.currentTimeMillis();
                long cost = (end - start) / 1000L;
                System.out.println("Cost " + cost + " sceconds");
                System.out.println(x);
            }, System.out::println);
        Thread.sleep(20000L);
    }
}
