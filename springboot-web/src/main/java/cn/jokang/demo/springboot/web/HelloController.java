package cn.jokang.demo.springboot.web;

import cn.jokang.demo.springboot.web.eventdemo.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jokang
 * @date 2020-06-28
 */
@RestController
public class HelloController {
    @Autowired
    private CustomEventPublisher customEventPublisher;

    @RequestMapping("/hi")
    public String hi() {
        return "Hi";
    }

    @RequestMapping("/event")
    public String publish(String msg) {
        customEventPublisher.publish(msg);
        return "done";
    }

    @RequestMapping(path = "/jsonString", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody()
    public String jsonString() {
        return "{\"desc\": \"this is a json string.\"}";
    }
}
