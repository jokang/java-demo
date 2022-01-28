package cn.jokang.demo.spring3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/version")
public class VersionController {
    @RequestMapping({"/", "/latest"})
    @ResponseBody
    public String latestVersion() {
        return "{\"version\":\"1.0.1\"}";
    }
}
