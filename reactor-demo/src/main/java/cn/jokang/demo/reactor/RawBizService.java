package cn.jokang.demo.reactor;

import cn.jokang.demo.reactor.annotation.RInput;
import cn.jokang.demo.reactor.annotation.ROutput;

/**
 * @author jokang
 * @date 2021/2/1
 */
public class RawBizService {
    @ROutput(name="recall", convertor = "xxx")
    public String hello(@RInput(name="userProfile") String input) {
        Context.getWriter.write();
        return input + " processed";
    }


    @ROutput(name="merged", convertor = "xxx")
    public DataFrame merge(@RInput(name="recalls") DataFrame recalls) {
        Context.getWriter.write();
        return input + " processed";
    }
}
