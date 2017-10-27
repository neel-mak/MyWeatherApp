package cmpe277.com.myweatherapp;

/**
 * Created by NEEL on 26-10-2017.
 */

public class CityList {

    private String CityName;
    private String CityTime;
    private String CityTemp;

    public CityList(String cityTime, String cityName, String cityTemp) {
        CityName = cityName;
        CityTime = cityTime;
        CityTemp = cityTemp;
    }

    public String getCityName() {
        return CityName;
    }

    public String getCityTime() {
        return CityTime;
    }

    public String getCityTemp() {
        return CityTemp;
    }

}
