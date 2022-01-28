package cn.jokang.demo.es5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * https://reflectoring.io/bean-validation-with-spring-boot/
 *
 * curl -X POST -d "{\"numberBetweenOneAndTen\":5,\"ipAddress\":\"192.168.\"}" -H "Content-Type:application/json" http://localhost:8080/validateBody
 * @author zhoukang
 * @date 2020-03-21
 */
@RestController
class ValidateRequestBodyController {
    @Autowired
    private ValidatingService service;

    @PostMapping("/validateBody")
    @ResponseBody
    String validateBody(@Valid @RequestBody Person input) {
        return "done";
    }

    @GetMapping("/hi2")
    String hi() {
        Person p = new Person();
        service.validateInput(p);
        return "ok";
    }



}
