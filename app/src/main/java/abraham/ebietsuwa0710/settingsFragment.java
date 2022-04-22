//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class settingsFragment extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RadioButton timeFormat = view.findViewById(R.id.time);
        RadioButton temp = view.findViewById(R.id.tem);
        Switch aSwitch = view.findViewById(R.id.set_is_lock);
        Button button = view.findViewById(R.id.save_b);
//        Toast.makeText(getContext(), "lllll", Toast.LENGTH_SHORT).show();

        String preferencesName = "abrahamPreferencesName";

        SharedPreferences sharedpreferences = getContext().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        String sTime = sharedpreferences.getString("abrahamTime", "");
        String sTemp = sharedpreferences.getString("abrahamTemp", "");

        if (sTemp != null){

            if (sTemp.equals("celsius")){

                temp.setChecked(true);
                temp.setText("celsius");

            }else {
                temp.setChecked(false);
                temp.setText("Fahrenheit");
            }

        }if (sTime != null){

            if (sTime.equals("12hr")){

                timeFormat.setChecked(true);
                timeFormat.setText("time is 12hr");
            }else {
                timeFormat.setChecked(false);
                timeFormat.setText("time is 24hr");
            }

        }

        timeFormat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    editor.putString("abrahamTime", "12hr");

                    timeFormat.setText("time is 12hr");

                } else {
                    editor.putString("abrahamTime", "24hr");

                    timeFormat.setText("time is 24hr");
//                    Toast.makeText(getContext(), "ppppp", Toast.LENGTH_SHORT).show();
                }

            }
        });

        temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor.putString("abrahamTemp", "celsius");

                    temp.setText("celsius");

                } else {
                    editor.putString("abrahamTemp", "Fahrenheit");

                    temp.setText("Fahrenheit");
                }
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                } else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "lllll", Toast.LENGTH_SHORT).show();

                editor.commit();
            }
        });


        return view;

    }
}
