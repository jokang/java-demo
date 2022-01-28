package cn.jokang.demo.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zhoukang
 * @date 2020/10/20
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDto(Car car);

    @Mapping(source = "seatCount", target = "numberOfSeats")
    Car fromDto(CarDTO carDto);
}
