package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

        final String value =  modifyTextProductName.getText().toString();

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQL adminSQL = new AdminSQL(getActivity(), "test.db", null, 1);
                SQLiteDatabase db = adminSQL.getWritableDatabase();
                if (!modifyTextProductName.getText().toString().matches("")
                        && !modifyTextProductCategory.getText().toString().matches("")
                        && !modifyTextProductPrice.getText().toString().matches("")) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("productName", modifyTextProductName.getText().toString());
                    contentValues.put("productCategory", modifyTextProductCategory.getText().toString());
                    contentValues.put("productPrice", modifyTextProductPrice.getText().toString());
                    if (db.update("product", contentValues,
                            "productName='"+value+"'", null) > 0) {
                        Toast.makeText(getActivity(), "register edited!", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = new FragmentListProduct();
                        fragmentTransaction.replace(R.id.main_fragment, fragment);
                        fragmentTransaction.commit();
                    } else
                        Toast.makeText(getActivity(), "unable to edit", Toast.LENGTH_SHORT).show();
                    db.close();

                } else
                    Toast.makeText(getActivity(), "missing value!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
