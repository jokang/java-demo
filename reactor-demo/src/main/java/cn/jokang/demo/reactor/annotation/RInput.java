package cn.jokang.demo.reactor.annotation;

/**
 * @author jokang
 */
public @interface RInput {
    String name() default "";
    String convertor() default "";
}
