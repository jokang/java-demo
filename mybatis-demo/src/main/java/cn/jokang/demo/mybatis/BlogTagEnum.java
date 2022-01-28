package cn.jokang.demo.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukang
 * @date 2019-10-23
 */
public enum BlogTagEnum {
    /**
     * S
     */
    S(1),

    /**
     * A
     */
    A(2),
    /**
     * B
     */
    B(3),
    /**
     * C
     */
    C(4),
    ;

//    private static final Map<Integer, BlogTagEnum> VALUE_MAP = new HashMap<Integer, BlogTagEnum>() {{
//        put(1, S);
//        put(2, A);
//        put(3, A);
//        put(4, A);
//    }};

    private static final Map<Integer, BlogTagEnum> VALUE_MAP = new HashMap<>();

    static {
        for (BlogTagEnum tag : BlogTagEnum.values()) {
            VALUE_MAP.put(tag.getValue(), tag);
        }
    }

    int value;

    BlogTagEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BlogTagEnum fromValue(int val) {
        return VALUE_MAP.get(val);
    }
}
