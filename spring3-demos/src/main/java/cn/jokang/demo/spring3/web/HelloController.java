package cn.jokang.demo.spring3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jokang
 */
@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping({"/brain/magicmirror/beeapi/diagnose/r/tmpForDev", "/magicmirror/diagnose/r/tmpForDev"})
    @ResponseBody
    public String testBeeApi(String source) {
        return "{\"status\":\"success\"}";
    }

    @RequestMapping("closeOutputStreamInController")
    @ResponseBody
    public String closeOutputStreamInController(HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.print("Output before output stream closed.");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Output after output stream closed.";
    }

    @RequestMapping("returnNothing")
    public void returnNothing(HttpServletResponse response) {

    }

    @RequestMapping("testChineseCharacters")
    @ResponseBody
    public String testChinese() {
        // 被@ResponseBody注解的方法，都会经过MessageConverter转换
        // String默认使用StringHttpMessageConverter转换，默认编码是ISO-8859-1
        return "Here is the Chinese characters: 中文";
    }
}