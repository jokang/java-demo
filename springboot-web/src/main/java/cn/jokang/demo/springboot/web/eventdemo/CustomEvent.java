package cn.jokang.demo.springboot.web.eventdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jokang
 * @date 2020/11/26
 */
@Data
@AllArgsConstructor
public class CustomEvent {
    private String msg;
}
