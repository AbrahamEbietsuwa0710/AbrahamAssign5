package abraham.ebietsuwa0710.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import abraham.ebietsuwa0710.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
//    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView dateText = root.findViewById(R.id.the_date);

        DateFormat dateFormat = new SimpleDateFormat();
//        Toast.makeText(getActivity(), "pppppppppppppppppppppppppppppppppppppppppppppppppppp", Toast.LENGTH_LONG).show();

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        dateText.setText(formatter.format(date));

        String preferencesName = "abrahamPreferencesName";

        EditText someInfo = root.findViewById(R.id.some_info);
        Button saveToFile = root.findViewById(R.id.save_b);

//        File path = getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//        File file = new File(path, "abraham.txt");

        saveToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theInfo = someInfo.getText().toString();

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }


                if (!theInfo.trim().isEmpty()) {
                    try {

                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("abraham.txt", Context.MODE_PRIVATE));
                        outputStreamWriter.append(theInfo);
                        outputStreamWriter.close();

                        Toast.makeText(getContext(), "File saved", Toast.LENGTH_SHORT).show();
                        saveToFile.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "File not saved", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getContext(), "File not saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

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