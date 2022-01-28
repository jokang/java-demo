package cn.jokang.demos;

import lombok.*;
import org.junit.Test;

/**
 * https://www.baeldung.com/lombok-getter-boolean
 * Lombok 如何给POJO添加布尔字段的getter
 *
 * @author zhoukang
 * @date 2019-03-28
 */
public class LombokTests {
    @Builder
    @ToString
    public static class Pojo {
        @Getter
        private boolean primitiveBoolean;
        @Getter
        private Boolean wrappedBoolean;
    }

    @Test
    public void testPojo() {
        Pojo p = Pojo.builder().build();
        p.isPrimitiveBoolean();
        p.getWrappedBoolean();
    }

    @Test(expected = NullPointerException.class)
    public void testNonNull() {
        nonNullMethod(null);
    }

    private void nonNullMethod(@NonNull String s) {

    }

    @RequiredArgsConstructor(staticName = "supllier")
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor
    public static class Shape {
        private int x;
        @NonNull
        private double y;
        @NonNull
        private String name;
    }
    public void testShape() {
        Shape shape = Shape.supllier(1, "n");
    }

    @Test
    public void testToString() {
        Pojo pojo = new Pojo(false, true);
        System.out.println(pojo);
    }
}
