package cn.jokang.demo.es5;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zhoukang
 * @date 2020-03-21
 */
public class Person {
    @NotNull
    @Min(8)
    @Max(9)
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "age=" + age +
            '}';
    }
}
