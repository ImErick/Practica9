package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by erickgarcia on 17/03/18
 */

public class FragmentAddUProduct extends Fragment {
    private EditText editTextProductName, editTextProductCategory, editTextProductPrice;
    private Button button_save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextProductCategory = view.findViewById(R.id.editTextProductCategory);
        editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        button_save = view.findViewById(R.id.btnSaveProduct);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
