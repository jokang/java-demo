package cn.jokang.demo.mbg.gen;

import cn.jokang.demo.mbg.gen.domain.DomainTemplate;
import cn.jokang.demo.mbg.gen.mapper.DomainTemplateMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GeneratedMapperTests {
    private SqlSession session;

    @Before
    public void setup() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            Assert.fail("Fail to read the configuration.");
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        System.out.println("session initialized.");
    }

    @After
    public void after() {
        if (Objects.nonNull(session)) {
            session.close();
            System.out.println("session closed.");
        }
    }

    @Test
    public void testSelectByPrimaryKey() {
        DomainTemplateMapper mapper = session.getMapper(DomainTemplateMapper.class);
        DomainTemplate domainTemplate = mapper.selectByPrimaryKey(-1L);
        Assert.assertNull(domainTemplate);
    }

}
