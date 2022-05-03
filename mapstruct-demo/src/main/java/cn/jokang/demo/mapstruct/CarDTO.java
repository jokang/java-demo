package cn.jokang.demo.mapstruct;


/**
 * @author zhoukang
 * @date 2020/10/20
 */
public class CarDTO {

    public CarDTO(String make, int seatCount, String type) {
        this.make = make;
        this.seatCount = seatCount;
        this.type = type;
    }

    private String make;
    private int seatCount;
    private String type;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
