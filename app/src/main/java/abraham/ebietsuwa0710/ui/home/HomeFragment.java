package abraham.ebietsuwa0710.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import abraham.ebietsuwa0710.R;
import abraham.ebietsuwa0710.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
//    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        TextView dateText = root.findViewById(R.id.the_date);

//        DateFormat dateFormat = new SimpleDateFormat();
        Toast.makeText(getActivity(), "pppppppppppppppppppppppppppppppppppppppppppppppppppp", Toast.LENGTH_LONG).show();

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        dateText.setText(formatter.format(date));

        String preferencesName = "abrahamPreferencesName";

//        SharedPreferences sharedpreferences = getContext().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
//
//        String sTime = sharedpreferences.getString("abrahamTime", "");

//        if (sTime != null) {
//            Toast.makeText(getContext(), "ggggg", Toast.LENGTH_LONG).show();
//
//            if (sTime.equals("12hr")) {

//                binding.AbrahamTextClock.setFormat12Hour("hh:mm:ss a");
//                binding.AbrahamTextClock.setFormat24Hour(null);
//                Toast.makeText(getContext(), "ggggg", Toast.LENGTH_LONG).show();


//            } else {
//                binding.AbrahamTextClock.setFormat24Hour("hh:mm:ss a");
//                binding.AbrahamTextClock.setFormat12Hour(null);
//                Toast.makeText(getContext(), "kkk", Toast.LENGTH_LONG).show();

//            }
//        }

        return root;
    }

}