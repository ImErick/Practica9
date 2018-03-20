package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by erickgarcia on 17/03/18
 */

public class ItemProductAdapter extends RecyclerView.Adapter <ItemProductAdapter.ViewHolder>{
    Context context;
    ArrayList<ItemProduct> itemProductArrayList;
    LayoutInflater inflater;

    public ItemProductAdapter(Context context, ArrayList<ItemProduct> itemProductArrayList) {
        this.context = context;
        this.itemProductArrayList = itemProductArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder =  new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemProductName.setText(itemProductArrayList.get(position).getProductName());
        holder.itemProductCategory.setText(itemProductArrayList.get(position).getProductCategory());
        holder.itemProductPrice.setText("$" + String.valueOf(itemProductArrayList.get(position).getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return itemProductArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemProductName, itemProductCategory, itemProductPrice;
        ImageButton imageButtonUpdate, imageButtonDelete;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemProductName = itemView.findViewById(R.id.textViewProductName);
            itemProductCategory = itemView.findViewById(R.id.textViewProductCategory);
            itemProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            imageButtonUpdate = itemView.findViewById(R.id.imageButtonEdit);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);

            imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminSQL adminSQL = new AdminSQL(view.getContext(), "test.db", null, 1);
                    SQLiteDatabase db = adminSQL.getWritableDatabase();
                    if (db.delete("product", "productName='"+itemProductName.getText().toString()+"'", null) > 0) {
                        Toast.makeText(view.getContext(), "product deleted", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = ((MainActivity)context).getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = new FragmentListProduct();
                        fragmentTransaction.replace(R.id.main_fragment, fragment);
                        fragmentTransaction.commit();
                    } else
                        Toast.makeText(view.getContext(), "no me deja eliminar la wea", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            });

            imageButtonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("productName", itemProductName.getText().toString());
                    bundle.putString("productCategory", itemProductCategory.getText().toString());
                    bundle.putString("productPrice", itemProductPrice.getText().toString());
                    FragmentManager fragmentManager = ((MainActivity)context).getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = new FragmentEditProduct();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_fragment, fragment);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
