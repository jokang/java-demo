package cn.jokang.demo.mapstruct;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;



/**
 * @author zhoukang
 * @date 2020/10/20
 */
public class MapStructTests {
    @Test
    public void testCarToDto() {
        //given
        Car car = new Car("Morris", 5, CarType.A);

        //when
        CarDTO carDto = CarMapper.INSTANCE.carToCarDto(car);
        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getMake()).isEqualTo("Morris");
        assertThat(carDto.getSeatCount()).isEqualTo(5);
        assertThat(carDto.getType()).isEqualTo("A");
    }

    @Test
    public void testDtoToCar() {
        //given
        CarDTO carDto = new CarDTO("Morris", 5, "A");

        //when
        Car car = CarMapper.INSTANCE.fromDto(carDto);
        //then
        assertThat(car).isNotNull();
        assertThat(car.getMake()).isEqualTo("Morris");
        assertThat(car.getNumberOfSeats()).isEqualTo(5);
        assertThat(car.getType()).isEqualTo(CarType.A);
    }
}
