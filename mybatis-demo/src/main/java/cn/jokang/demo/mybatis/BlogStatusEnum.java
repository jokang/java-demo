package cn.jokang.demo.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-10-23
 */
public enum BlogStatusEnum {
    /**
     * valid
     */
    VALID(1),

    /**
     * invalid
     */
    INVALID(0);

    private static final Map<Integer, BlogStatusEnum> VALUE_MAP = new HashMap<Integer, BlogStatusEnum>() {{
        put(1, VALID);
        put(0, INVALID);
    }};

    int value;

    BlogStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BlogStatusEnum fromValue(int val) {
        return VALUE_MAP.get(val);
    }
}
