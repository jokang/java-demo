package cn.jokang.demo.spring3.web;

import cn.jokang.demo.spring3.vo.JsonResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试Spring如何解析参数
 *
 * @author zhoukang
 * @date 2019-02-19
 */
@Controller
@RequestMapping("/param-parser")
public class ParameterParserController {
    private final Logger logger = LoggerFactory.getLogger(ParameterParserController.class);

    /**
     * 测试String参数的传递。
     * ?ss=或者?ss，传回来的是""
     * 没有ss的时候，传回来的是null
     */
    @RequestMapping(value = "/string", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object testParseString(String ss) {
        logger.info("ss={}", ss);
        System.out.println(ss);
        return new JsonResponseVO(1);
    }
}
