//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

//import androidx.fragment.app.Fragment

public class downloadsFragment extends Fragment {
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloads, container, false);

        String[] listViewArray = {"flower","nature","mars"};

        ListView listView = view.findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),R.layout.list_item,listViewArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });

        return view;
    }
}

