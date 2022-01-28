package cn.jokang.demo.springboot.web.testdemo;

import org.springframework.stereotype.Service;

/**
 * @author jokang
 * @date 2020/12/12
 */
@Service
public class GreetingService {
    public String greet() {
        return "Hello, World";
    }
}
