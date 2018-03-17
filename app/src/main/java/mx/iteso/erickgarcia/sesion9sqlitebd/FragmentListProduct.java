package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by erickgarcia on 17/03/18:
 * TODO: mover todos los elementos que tengan que ver con la base de datos a AdminSQL en una chance
 */

public class FragmentListProduct extends Fragment {
    RecyclerView recyclerView;
    ItemProductAdapter itemProductAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewListProducts);

        //informacion necesaria para mostrar obtener y mostrar los productos en el Recycler View
        ArrayList<ItemProduct> itemProductArrayList = new ArrayList<>();
        String query = "SELECT * FROM product";
        AdminSQL adminSQL = new AdminSQL(getActivity(), "test.db", null, 1);
        SQLiteDatabase db = adminSQL.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                ItemProduct itemProduct = new ItemProduct();
                itemProduct.setProductName(cursor.getString(1));
                itemProduct.setProductCategory(cursor.getString(2));
                itemProduct.setProductPrice(cursor.getFloat(3));
                itemProductArrayList.add(itemProduct);
            } while (cursor.moveToNext());
        }

        itemProductAdapter = new ItemProductAdapter(getActivity(), itemProductArrayList);
        recyclerView.setAdapter(itemProductAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        db.close();
        return view;
    }
}
