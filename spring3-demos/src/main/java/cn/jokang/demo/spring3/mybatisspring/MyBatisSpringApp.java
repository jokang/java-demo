package cn.jokang.demo.spring3.mybatisspring;

import cn.jokang.demo.mbg.gen.mapper.DomainTemplateMapper;
import cn.jokang.demo.spring3.xmlcontext.SomeBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示MyBatis和Spring整合
 *
 * @author zhoukang
 * @date 2019-02-22
 */
public class MyBatisSpringApp {
    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("mybatis-spring/datasource-context.xml");
        SqlSession someBean = (SqlSession) ctx.getBean("sqlSession");

        DomainTemplateMapper mapper = someBean.getMapper(DomainTemplateMapper.class);
        System.out.println(mapper.selectByPrimaryKey(-1L));
    }
}
