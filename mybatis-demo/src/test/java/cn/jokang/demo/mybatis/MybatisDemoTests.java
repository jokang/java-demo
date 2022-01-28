package cn.jokang.demo.mybatis;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 1.定义MyBatis配置
 * 2.定义Mapper接口
 * 3.定义行为.可以在xml文件里面定义,也可以直接在Mapper接口,使用注解定义
 */
@Slf4j
public class MybatisDemoTests {
    private SqlSession session;

    @Before
    public void setup() {
        String resource = "mybatis-standalone-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            Assert.fail("Fail to read the configuration.");
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        log.info("session initialized.");
    }

    @After
    public void after() {
        if (Objects.nonNull(session)) {
            session.close();
            log.info("session closed.");
        }
    }

    // 定义映射实现,方式1:使用XML文件。XML文件的位置定义在MyBatis配置文件中.
    // 1.1用完整路径找方法
    @Test
    public void testSelectWithSession() throws IOException {
        Blog blog = session.selectOne("cn.jokang.demo.mybatis.BlogMapper.selectBlog", 1);
        log.info(blog.toString());
    }

    // 1.2用Mapper接口找方法
    @Test
    public void testSelectWithXml() {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
        log.info(blog.toString());
    }

    // 定义映射实现,方式2:使用Annotation。
    // 2.1 selectBlog2方法是加上了@Select注解的。
    @Test
    public void testSelectWithAnnotation() {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogWithAnnotation(1);
        log.info(blog.toString());
    }

    @Test
    public void testFindAll() {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.findAll();
        log.info(String.valueOf(blogs));
    }

    // 演示MyBatis如何使用like查询
    @Test
    public void testLikeQuery() {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.findByContentLike("test");
    }

    @Test
    public void testInsert() {
        Blog blog = new Blog();
        blog.setContent(new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date()));
        blog.setStatus(BlogStatusEnum.VALID);
        List<BlogTagEnum> tags = new ArrayList<>();
        tags.add(BlogTagEnum.A);
        tags.add(BlogTagEnum.B);
        blog.setTags(tags);

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        log.info("mapper.insert returns {}", mapper.insert(blog));
        log.info("blog.getId()={}", blog.getId());
        session.commit();
    }

    @Test
    public void testBatchInsert() {
        Blog blog1 = new Blog();
        blog1.setContent(new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date()));
        blog1.setStatus(BlogStatusEnum.VALID);
        List<BlogTagEnum> tags = new ArrayList<>();
        tags.add(BlogTagEnum.A);
        blog1.setTags(tags);

        Blog blog2 = new Blog();
        blog2.setContent(new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date()));
        blog2.setStatus(BlogStatusEnum.VALID);
        blog2.setTags(tags);

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        mapper.batchInsert(Lists.newArrayList(blog1, blog2));
        System.out.println(blog1.getId());
        System.out.println(blog2.getId());
        session.commit();
    }

    @Test
    public void testUpdateStatus() {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        mapper.updateStatus(16, null, 0);
        mapper.updateStatus(17, "content v2", 0);
        session.commit();
    }
}
