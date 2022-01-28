package cn.jokang.demo.mybatis;

import java.util.List;

/**
 * Created by zhoukang on 18/11/18.
 */
public class Blog {
    private int id;
    private List<BlogTagEnum> tags;
    private String content;
    private BlogStatusEnum status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BlogTagEnum> getTags() {
        return tags;
    }

    public void setTags(List<BlogTagEnum> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BlogStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id=" + id +
            ", tags=" + tags +
            ", content='" + content + '\'' +
            ", status=" + status +
            '}';
    }
}
