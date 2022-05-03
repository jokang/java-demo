package cn.jokang.demo.springboot.web.eventdemo;

/**
 * @author jokang
 * @date 2020/11/26
 */
public class CustomEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CustomEvent(String msg) {
        this.msg = msg;
    }
}
