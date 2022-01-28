package cn.jokang.demo.es5;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author zhoukang
 * @date 2020-03-21
 */
@Service
@Validated
class ValidatingService{

    void validateInput(@Valid Person input){
        System.out.println(input);
    }

}
