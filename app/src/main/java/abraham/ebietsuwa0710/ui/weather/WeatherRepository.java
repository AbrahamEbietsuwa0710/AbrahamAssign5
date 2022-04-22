package abraham.ebietsuwa0710.ui.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {

    private static final String TAG = "WeatherRepository";

    private Retrofit retrofit;
    public MutableLiveData<WeatherPlaceholder> weatherPlaceholderMutableLiveData = new MutableLiveData<>();

    public void getWeather(double lat, double lon, String apiKey, Context context) {



        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Map<String,Object> map = new HashMap<>();
        map.put("lat",lat);
        map.put("lon",lon);
        map.put("appid",apiKey);

        apiClient.getWeather(map).enqueue(new Callback<WeatherPlaceholder>() {
            @Override
            public void onResponse(@NonNull Call<WeatherPlaceholder> call, @NonNull Response<WeatherPlaceholder> response) {
                Log.d(TAG, "getWeather: "+response.code());
                if (response.code() == 200){

                    weatherPlaceholderMutableLiveData.setValue(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<WeatherPlaceholder> call, @NonNull Throwable t) {
                Toast.makeText(context, "Unable to connect to api", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
