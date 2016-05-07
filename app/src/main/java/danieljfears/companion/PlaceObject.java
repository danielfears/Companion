package danieljfears.companion;

import java.io.Serializable;

public class PlaceObject implements Serializable{

    private String placeName;
    private Integer placeDist;

    public String getPlaceName() {
        return placeName;
    }

    public Integer getPlaceDist() { return placeDist; }

    public PlaceObject(String placeName, Integer placeDist) {
        this.placeName = placeName;
        this.placeDist = placeDist;
    }
}