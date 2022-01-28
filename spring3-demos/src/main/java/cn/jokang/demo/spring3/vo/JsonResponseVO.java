package cn.jokang.demo.spring3.vo;

/**
 * @author zhoukang
 * @date 2019-02-19
 */
public class JsonResponseVO {
    private Integer code;

    public JsonResponseVO() {
    }

    public JsonResponseVO(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "JsonResponseVO{" +
            "code=" + code +
            '}';
    }
}
