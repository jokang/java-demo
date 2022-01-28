package cn.jokang.demos.fastjson;

/**
 * @author zhoukang
 * @date 2020-06-03
 */
public class Foo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" +
            "name='" + name + '\'' +
            '}';
    }
}
