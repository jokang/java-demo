package cn.jokang.demos.mock;

import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-11-29
 */
public class MockRemoteService {
    public String processing(String param) {
        try {
            System.out.println("Input " + param);
            Thread.sleep(3000);
            System.out.println("Done the work " + param);
            return "Done";
        } catch (InterruptedException e) {
            return "ERROR";
        }
    }
}
