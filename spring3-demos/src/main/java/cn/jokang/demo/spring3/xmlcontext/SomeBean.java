package cn.jokang.demo.spring3.xmlcontext;

/**
 * @author zhoukang
 * @date 2019-02-22
 */
public class SomeBean {
    private String name;

    public void init() {
        System.out.println(this.getClass().getSimpleName() + " " + name + " was initialized.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
