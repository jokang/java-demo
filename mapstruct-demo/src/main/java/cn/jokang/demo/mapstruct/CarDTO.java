package cn.jokang.demo.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoukang
 * @date 2020/10/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;
}
