package cn.jokang.demo.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    Blog selectBlog(int id);

    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlogWithAnnotation(int id);

    List<Blog> findAll();

    List<Blog> findByContentLike(String keyword);

    int insert(Blog blog);

    int batchInsert(List<Blog> blogs);

    void updateStatus(@Param("id") int id, @Param("content") String content, @Param("status")int status);
}