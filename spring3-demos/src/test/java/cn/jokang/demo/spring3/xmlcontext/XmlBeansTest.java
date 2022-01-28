package cn.jokang.demo.spring3.xmlcontext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 演示单测如何从文件中加载Bean
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration("classpath:xmlcontext/context-beans.xml")
public class XmlBeansTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected SomeBean someBean;

    /**
     * 每次@Test注解的方法运行之前都会运行
     */
    @Before
    public void setup() {
        System.out.println("Setup tests.");
    }

    @Test
    public void simpleTest() throws Exception {
        System.out.println(someBean.getName());
    }

    @Test
    public void simpleTestAgain() throws Exception {
        System.out.println(someBean.getName());
    }
}
