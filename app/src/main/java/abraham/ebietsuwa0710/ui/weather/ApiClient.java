package abraham.ebietsuwa0710.ui.weather;


import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiClient {

    @GET("data/2.5/weather")
    Call<WeatherPlaceholder> getWeather(@QueryMap Map<String, Object> map);

}
