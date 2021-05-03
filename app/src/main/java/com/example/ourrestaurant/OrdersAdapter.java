package com.example.ourrestaurant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    Activity activity;
    List<ordersData> oData;
    RoomDb rest;
    public OrdersAdapter(Orders orders, List<ordersData> orders1) {
        this.activity = orders;
        this.oData = orders1;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_list_item,parent,false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        rest = RoomDb.getInstance(activity);
        ordersData order = oData.get(position);
        holder.dateTime.setText(order.dateTime);
        holder.orderVal.setText("$ "+Double.toString(order.orderTotal));
        String[] ordItem = order.items.split(",");
        String ite = "";
        for (String x : ordItem){
            String[] itemIdQty = x.split(":");
            ite+="x"+itemIdQty[1]+"\t"+((restInterface)activity).getMenuItem(Integer.parseInt(itemIdQty[0])).itemName+"\n";
        }
        holder.orderItems.setText(ite);
    }

    @Override
    public int getItemCount() {
        return oData.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime;
        TextView orderVal;
        TextView orderItems;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTime = itemView.findViewById(R.id.txt_dateTime);
            orderVal = itemView.findViewById(R.id.txt_ordVal);
            orderItems = itemView.findViewById(R.id.txt_ordItems);
        }
    }
}
