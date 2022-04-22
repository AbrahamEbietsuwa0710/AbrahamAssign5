//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class shapeFragment extends Fragment {

    FirebaseFirestore firebaseFirestore;
    AutoCompleteTextView autoCompleteTextView;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shape, container, false);

        autoCompleteTextView = view.findViewById(R.id.email);
        Switch aSwitch = view.findViewById(R.id.a_switch);


        firebaseFirestore = FirebaseFirestore.getInstance();

        String[] email_array = getResources().getStringArray(R.array.emails);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, email_array);// ArrayAdapter.createFromResource(getContext(),R.array.emails, android.R.layout.simple_spinner_dropdown_item);

        autoCompleteTextView.setAdapter(adapter);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {

                    //send to firebase
                    String email = autoCompleteTextView.getText().toString();

                    if (!email.trim().isEmpty()) {
                        onStore(email);
                    }


                } else {
                    //retrieve from firebase

                    onRetrieve();

                }

            }
        });

        return view;
    }

    private void onStore(String email) {

        CollectionReference collectionReference = firebaseFirestore.collection("email");

        DocumentReference documentReference = collectionReference.document("id");

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        documentReference.set(map);
        autoCompleteTextView.setText("");

    }

    private void onRetrieve() {

        CollectionReference collectionReference = firebaseFirestore.collection("email");

        DocumentReference documentReference = collectionReference.document("id");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String email = (String) documentSnapshot.get("email");

                if (email!= null){
                    autoCompleteTextView.setText(email);
                }

            }
        });

    }

}