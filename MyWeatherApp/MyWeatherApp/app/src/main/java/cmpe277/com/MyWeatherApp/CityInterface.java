package cmpe277.com.MyWeatherApp;

import cmpe277.com.MyWeatherApp.model.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NEEL on 03-11-2017.
 */


public interface CityInterface {
    @GET("data/2.5/weather")
    Call<WeatherData> sendCity(@Query("q") String cityname,
                               @Query("units") String unit,
                               @Query("appid") String key);
}
