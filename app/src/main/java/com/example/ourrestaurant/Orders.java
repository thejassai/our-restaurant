package com.example.ourrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class Orders extends AppCompatActivity implements restInterface{

    RecyclerView orderView;
    RoomDb restDb;
    List<ordersData> orders;
    ImageView btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        restDb = RoomDb.getInstance(this);

        orders = restDb.ordersDao().getAll();

        orderView = findViewById(R.id.rVOrders);
        orderView.setLayoutManager(new LinearLayoutManager(this));
        orderView.setAdapter(new OrdersAdapter(Orders.this,orders));

        btn_close = findViewById(R.id.btnClose_oA);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.headFrame_oA,new headerFragment())
                .commit();
    }

    @Override
    public void deleteItem(int id) {

    }

    @Override
    public menuData getMenuItem(int itemId) {
        return restDb.restaurantDao().getItemById(itemId);
    }

    @Override
    public void setBillDet() {

    }
}