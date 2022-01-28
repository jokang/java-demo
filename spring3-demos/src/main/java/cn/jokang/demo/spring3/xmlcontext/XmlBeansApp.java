package cn.jokang.demo.spring3.xmlcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示从xml文件中加载bean
 *
 * @author zhoukang
 * @date 2019-02-22
 */
public class XmlBeansApp {
    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("xmlcontext/context-beans.xml");
        SomeBean someBean = (SomeBean) ctx.getBean("fakeBean");
        System.out.println(someBean.getName());
    }
}
