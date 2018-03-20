package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by erickgarcia on 19/03/18
 */

public class FragmentEditProduct extends Fragment {
    private EditText modifyTextProductName, modifyTextProductCategory, modifyTextProductPrice;
    private Button button_edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_product, container, false);
        modifyTextProductName = view.findViewById(R.id.modifyTextProductName);
        modifyTextProductCategory = view.findViewById(R.id.modifyTextProductCategory);
        modifyTextProductPrice = view.findViewById(R.id.modifyTextProductPrice);
        button_edit = view.findViewById(R.id.btnModifyProduct);

        modifyTextProductName.setText(getArguments().getString("productName"));
        modifyTextProductCategory.setText(getArguments().getString("productCategory"));
        modifyTextProductPrice.setText(getArguments().getString("productPrice"));

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("que contiene?", modifyTextProductName.getText().toString());
                AdminSQL adminSQL = new AdminSQL(getActivity(), "test.db", null, 1);
                SQLiteDatabase db = adminSQL.getWritableDatabase();
                if (!modifyTextProductName.getText().toString().matches("")
                        && !modifyTextProductCategory.getText().toString().matches("")
                        && !modifyTextProductPrice.getText().toString().matches("")) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("productName", modifyTextProductName.getText().toString());
                    contentValues.put("productCategory", modifyTextProductCategory.getText().toString());
                    contentValues.put("productPrice", modifyTextProductPrice.getText().toString());
                    db.update("product", contentValues, "productName="+modifyTextProductName.getText().toString(), null);
                    Toast.makeText(getActivity(), "register edited!", Toast.LENGTH_SHORT).show();
                    db.close();

                } else
                    Toast.makeText(getActivity(), "missing value!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
