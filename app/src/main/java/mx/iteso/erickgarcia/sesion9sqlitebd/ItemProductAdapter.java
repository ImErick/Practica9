package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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
        public ViewHolder(View itemView) {
            super(itemView);
            itemProductName = itemView.findViewById(R.id.textViewProductName);
            itemProductCategory = itemView.findViewById(R.id.textViewProductCategory);
            itemProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            imageButtonUpdate = itemView.findViewById(R.id.imageButtonEdit);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
        }
    }
}
