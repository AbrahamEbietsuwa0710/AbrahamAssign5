//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710.ui.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import abraham.ebietsuwa0710.R;

public class WeatherFragment extends Fragment {

    //where zero is Latitude and one is Longitude
    double[] abujaLonAndLat = {9.05785, 7.49508};
    double[] torontoLonAndLat = {43.651070, -79.347015};
    double[] londonLonAndLat = {51.509865, -0.118092};
    double[] montrealLonAndLat = {45.508888, -73.561668};
    double[] calgaryLonAndLat = {51.049999, -114.066666};

    String apiKey = "8ccdfec6d3a480e33d66907d9e12e0ee";

    WeatherViewModel viewModel;

    private String temp;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);

        String[] users = {"Select city", "Abuja", "Toronto", "London", "Montreal", "Calgary"};

        TextView result = view.findViewById(R.id.result);

        String preferencesName = "abrahamPreferencesName";

        SharedPreferences sharedpreferences = getContext().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);

        String sTemp = sharedpreferences.getString("abrahamTemp", "");

        if (sTemp != null) {

            if (sTemp.equals("celsius")) {

//                Toast.makeText(getContext(), "ttttt", Toast.LENGTH_SHORT).show();
                temp = "celsius";
            } else {
                temp = "fahrenheit";

            }

        }


        Spinner spin = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!users[i].equals("Select city")) {

                    if (users[i].equals("Abuja")) {

                        viewModel.getWeather(abujaLonAndLat[0], abujaLonAndLat[1], apiKey, getContext());

                    } else if (users[i].equals("Toronto")) {
                        viewModel.getWeather(torontoLonAndLat[0], torontoLonAndLat[1], apiKey, getContext());
                    } else if (users[i].equals("London")) {
                        viewModel.getWeather(londonLonAndLat[0], londonLonAndLat[1], apiKey, getContext());
                    } else if (users[i].equals("Montreal")) {
                        viewModel.getWeather(montrealLonAndLat[0], montrealLonAndLat[1], apiKey, getContext());
                    } else if (users[i].equals("Calgary")) {
                        viewModel.getWeather(calgaryLonAndLat[0], calgaryLonAndLat[1], apiKey, getContext());
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        viewModel.getWeatherPlaceholderLiveData().observe(getActivity(), new Observer<WeatherPlaceholder>() {
            @Override
            public void onChanged(WeatherPlaceholder weatherPlaceholder) {

                double theTemp = weatherPlaceholder.getMain().getTemp() ;

                double Celsius = ((theTemp-32)*5)/9;

                String newTemp;

                if (temp.equals("celsius")){
                newTemp =  "\nTemperature :" + Celsius+"C";
                }else {
               newTemp = "\nTemperature :" + weatherPlaceholder.getMain().getTemp() +"F";
                }

                String resultText = "Lon : " + weatherPlaceholder.getCoord().getLat() +
                        "\nLat :" + weatherPlaceholder.getCoord().getLat() +
                        "\nCountry : " + weatherPlaceholder.getSys().getCountry() +
                        "\nHumidity : " + weatherPlaceholder.getMain().getHumidity() + "%"
                        + newTemp+
                        "\nDescription : "+weatherPlaceholder.getWeather().get(0).getDescription();

                result.setText(resultText);

            }
        });

        return view;
    }
}

