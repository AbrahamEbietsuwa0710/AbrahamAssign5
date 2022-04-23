package abraham.ebietsuwa0710.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.FileWriter;
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
        TextClock textClock = root.findViewById(R.id.Abraham_textClock);

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

                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("abraham.txt", Context.MODE_APPEND));
                        outputStreamWriter.append(theInfo);
                        outputStreamWriter.close();


//                        outputStreamWriter.g

                        Toast.makeText(getContext(), "File saved", Toast.LENGTH_SHORT).show();
                        someInfo.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "File not saved", Toast.LENGTH_SHORT).show();

                    }


//                    try {
//
//                        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
//                        if (!root.exists()) {
//                            root.mkdirs();
//                        }
//                        File file = new File(root, "abraham" + ".txt");
//
//                        FileWriter fileWriter = new FileWriter(file, true);
//
//                        fileWriter.append(theInfo);
//                        fileWriter.flush();
//                        fileWriter.close();
//
//                        Toast.makeText(getContext(), "File saved", Toast.LENGTH_SHORT).show();
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getContext(), "File not saved", Toast.LENGTH_SHORT).show();
//
//                    }

                } else {
                    Toast.makeText(getContext(), "File not saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

        SharedPreferences sharedpreferences = getContext().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);

        String sTime = sharedpreferences.getString("abrahamTime", "");

        if (sTime != null) {

            if (sTime.equals("12hr")) {
                textClock.setFormat24Hour(null);
            } else {
                textClock.setFormat12Hour(null);
            }
        }

        Bitmap test = Bitmap.createBitmap(640, 200, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(test);
        // draw a dark gray background
        Paint backgroundPaint = new Paint();
        backgroundPaint.setARGB(255, 24, 24, 24);
        c.drawPaint(backgroundPaint);

        Path heart = new Path();
        // prepare a heart shape
        heart.moveTo(110, 175);
        heart.lineTo(10, 75);
        RectF leftCircle = new RectF(10, 25, 110, 125);
        heart.arcTo(leftCircle, 180, 180);
        RectF rightCircle = new RectF(110, 25, 210, 125);
        heart.arcTo(rightCircle, 180, 180);
        heart.close();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(18f);
        int[] colors = {
                0xFFFFFF88, // yellow
                0xFF0088FF, // blue
                0xFF000000, // black
                0xFFFFFF88  // yellow
        };
        float[] positions = {0.0f, 0.33f, 0.66f, 1.0f};
        // draw the left heart
        SweepGradient sweepGradient;
        { // initialize the sweep gradient
            sweepGradient = new SweepGradient(50, 50, colors, positions);
            paint.setShader(sweepGradient);
        }
        c.drawPath(heart, paint);
        c.drawText("SweepGradient", 50, 190, paint);

        return root;
    }

}