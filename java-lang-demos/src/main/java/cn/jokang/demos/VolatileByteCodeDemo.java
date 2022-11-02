package cn.jokang.demos;

/**
 * 看下volatile关键字对应的字节码
 */
public class VolatileByteCodeDemo {
    private static volatile int aVolatileInt = 0;

    public static void main(String[] args) {
        int a = 2;
        aVolatileInt = a + 1;
        System.out.println(aVolatileInt+ a);
    }
}
