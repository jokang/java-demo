package cn.jokang.demo.spring3.mis;

//import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 测试Apache Commons BeanUtils和Spring BeanUtils在copyProperties时的区别。
 */
public class BeanUtilsCopyTests {
    @Test
    public void testCommonBeanUtils() throws InvocationTargetException, IllegalAccessException {
        LongObject lo = new LongObject(1L);
        IntegerObject io = new IntegerObject();
        BeanUtils.copyProperties(io, lo);

        // Commons BeanUtils 可以将Long型域复制到Integer类型的
        Assert.assertEquals(io.getValue(), Integer.valueOf(1));
    }

    @Test
    public void testSpringBeanUtils1() {
        // Spring BeanUtils 不能将Long型域复制到Integer类型的
        LongObject lo = new LongObject(1L);
        IntegerObject io = new IntegerObject();
        // source和target的顺序不一样
        org.springframework.beans.BeanUtils.copyProperties(lo, io);

        Assert.assertNull(io.getValue());
    }

    @Test
    public void testSpringBeanUtils2() {
        // Spring BeanUtils 也不能将Integer型域复制到Long类型的
        LongObject lo = new LongObject();
        IntegerObject io = new IntegerObject(1);
        // source和target的顺序不一样
        org.springframework.beans.BeanUtils.copyProperties(io, lo);

        Assert.assertNull(lo.getValue());
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public static class LongObject {
        private Long value;

        public LongObject() {
        }

        public LongObject(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public static class IntegerObject {
        private Integer value;

        public IntegerObject() {

        }

        public IntegerObject(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
