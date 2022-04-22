package abraham.ebietsuwa0710.ui.weather;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository();
    }

    public void getWeather(double lat, double lon, String apiKey, Context context) {

        weatherRepository.getWeather(lat, lon, apiKey, context);

    }

    public LiveData<WeatherPlaceholder> getWeatherPlaceholderLiveData(){

        return weatherRepository.weatherPlaceholderMutableLiveData;

    }

}
