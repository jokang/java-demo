package cn.jokang.demo.es5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhoukang
 * @date 2019-11-28
 */
@Service
@Validated
public class ValidatorDemoService {
    @Autowired
    private Validator validator;

    public void validate() {
        Person p = new Person();
        p.setAge(1000);

        doWork(p);
    }

    public void doWork(@Valid Person p) {
        System.out.println(validator);

        System.out.println(p);
    }
}
