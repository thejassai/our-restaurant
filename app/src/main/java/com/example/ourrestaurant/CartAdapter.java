package com.example.ourrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Activity cartActivity;
    private List<cartData> cData;
    RoomDb restDb;
    public CartAdapter(cartActivity cartActivity, List<cartData> catData) {
        this.cartActivity = cartActivity;
        this.cData = catData;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        final cartData citem = cData.get(position);
        menuData menuData = ((restInterface) cartActivity).getMenuItem(citem.cartItemID);
        holder.itemName.setText(menuData.itemName);
        holder.itemPrice.setText("$ "+Integer.toString(menuData.price*citem.itemQty));
        holder.itemImage.setImageResource(menuData.foodPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cartActivity,ItemDetails.class);
                i.putExtra("cartData", (Serializable) citem);
                i.putExtra("update",true);
                cartActivity.startActivity(i);
            }
        });
        holder.itemQty.setText("x"+Integer.toString(citem.itemQty));
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((restInterface) cartActivity).deleteItem(citem.getID());
                cData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cData.size());
                ((restInterface) cartActivity).setBillDet();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cData.size();
    }

//    public class CartViewHolder extends RecyclerView.ViewHolder {
//        TextView itemID;
//        public CartViewHolder(@NonNull View itemView) {
//            super(itemView);
//            itemID = itemView.findViewById(R.id.test_text);
//        }
//    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;
        TextView itemQty;
        ImageView itemImage, btn_delete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName_cart);
            itemPrice = itemView.findViewById(R.id.itemPrice_cart);
            itemImage = itemView.findViewById(R.id.itemImage_cart);
            btn_delete = itemView.findViewById(R.id.btn_deleteItem);
            itemQty = itemView.findViewById(R.id.itemQty_cart);
        }
    }
}
