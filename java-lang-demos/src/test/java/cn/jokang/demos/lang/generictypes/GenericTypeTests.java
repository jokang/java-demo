package cn.jokang.demos.lang.generictypes;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * https://www.zhihu.com/question/20400700/answer/117464182
 * Effective Java 2nd 28 也有讲到
 *
 * @author zhoukang
 * @date 2019-03-25
 */
@Slf4j
public class GenericTypeTests {
    public static class Food {
    }

    public static class Fruit extends Food {
    }

    public static class Apple extends Fruit {
    }

    public static class Plate<T> {
        private T item;

        public Plate(T t) {
            this.item = t;
        }

        public void set(T t) {
            this.item = t;
        }

        public T get() {
            return item;
        }
    }

    @Test
    public void test() {
        // not work
        //Plate<Fruit> p = new Plate<Apple>(new Apple());

        // work
        Plate<Fruit> p = new Plate<Fruit>(new Apple());

        Plate<? extends Fruit> p2 = new Plate<Apple>(new Apple());
        log.info(p2.getClass().getName());
        Fruit f = p2.get();
        // 不能放东西
        // p2.set(new Fruit());

        Plate<? super Fruit> p3 = new Plate<>(new Fruit());
        log.info(p3.getClass().getName());
        p3.set(new Fruit());
        // 只能用Object取
        // Fruit f2 = p3.get();
        Object f2 = p3.get();
    }

}
