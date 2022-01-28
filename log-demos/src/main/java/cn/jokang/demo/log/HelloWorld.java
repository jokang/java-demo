package cn.jokang.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoukang
 * @date 2019-11-24
 */
public class HelloWorld {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World");
        Exception e = new RuntimeException("Mock Exception");
        logger.info("Mock Exception", e);
    }
}
