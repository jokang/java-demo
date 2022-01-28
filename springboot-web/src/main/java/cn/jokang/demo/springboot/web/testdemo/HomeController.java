package cn.jokang.demo.springboot.web.testdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to be tested.
 *
 * @author jokang
 * @date 2020/12/11
 */
@Controller
public class HomeController {
    @RequestMapping("/testdemo")
    @ResponseBody
    public String greeting() {
        return "Hello, World";
    }
}
