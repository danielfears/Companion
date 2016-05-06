package danieljfears.companion;

import java.io.Serializable;

public class ListObject implements Serializable{

    private String cityName;
    private Integer cityPicture;
    private String cityDesc;

    public String getCityName() {
        return cityName;
    }

    public Integer getCityPicture() { return cityPicture; }

    public String getCityDesc() { return cityDesc; }

    public ListObject(String cityName, Integer cityPicture, String cityDesc) {
        this.cityName = cityName;
        this.cityPicture = cityPicture;
        this.cityDesc = cityDesc;
    }
}