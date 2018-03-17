package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by erickgarcia on 17/03/18
 */

public class FragmentAddUProduct extends Fragment {
    private EditText editTextProductName, editTextProductCategory, editTextProductPrice;
    private Button button_save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextProductCategory = view.findViewById(R.id.editTextProductCategory);
        editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        button_save = view.findViewById(R.id.btnSaveProduct);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextProductName.getText().toString().matches("")
                        && !editTextProductCategory.getText().toString().matches("")
                        && !editTextProductPrice.getText().toString().matches("")) {
                    AdminSQL adminSQL = new AdminSQL(getActivity(), "test.db", null, 1);
                    SQLiteDatabase db = adminSQL.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("productName", editTextProductName.getText().toString());
                    contentValues.put("productCategory", editTextProductCategory.getText().toString());
                    contentValues.put("productPrice", editTextProductPrice.getText().toString());
                    if (db.insert("product", null, contentValues) > 0) {
                        Toast.makeText(getActivity(), "register created!", Toast.LENGTH_SHORT).show();
                        editTextProductName.setText("");
                        editTextProductCategory.setText("");
                        editTextProductPrice.setText("");
                        editTextProductName.requestFocus();
                    } else
                        Toast.makeText(getActivity(), "unable to create the register", Toast.LENGTH_SHORT).show();
                    db.close();
                } else
                    Toast.makeText(getActivity(), "missing value!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
