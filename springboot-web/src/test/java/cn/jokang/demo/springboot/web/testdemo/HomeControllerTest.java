package cn.jokang.demo.springboot.web.testdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A simple sanity check that will fail if the application context cannot start.
 */
@SpringBootTest
class HomeControllerTest {
    @Autowired
    private HomeController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}