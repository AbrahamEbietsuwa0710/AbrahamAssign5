//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class filecontentFragment extends Fragment {

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filecontent, container, false);

        TextView textViewFromFile = view.findViewById(R.id.content_txt);
        Button display = view.findViewById(R.id.display);
        Button delete = view.findViewById(R.id.delete);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                try {
                    InputStream inputStream = getContext().openFileInput("abraham.txt");

                    if (inputStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            stringBuilder.append("\n").append(receiveString);

                        }

                        inputStream.close();
                        result = stringBuilder.toString();
                        textViewFromFile.setText(result);

                        if (result.isEmpty()){
                            textViewFromFile.setText(result);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    textViewFromFile.setText("No content");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File out = new File("abraham.txt");

                try {
                    OutputStream os = new FileOutputStream(out);

                    String filePath = out.getAbsolutePath();
                    if (out.exists()) {
                        out.getCanonicalFile().delete();
                        if (out.exists()) {
                            getActivity().getApplicationContext().deleteFile(out.getName());
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }
}
