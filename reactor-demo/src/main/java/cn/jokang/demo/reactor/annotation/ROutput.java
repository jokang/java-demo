package cn.jokang.demo.reactor.annotation;

public @interface ROutput {
    String name() default "";
    String convertor() default "";
}
