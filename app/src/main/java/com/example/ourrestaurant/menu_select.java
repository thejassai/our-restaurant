package com.example.ourrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class menu_select extends AppCompatActivity {

    LinearLayout head;
    TextView menuType;
    private RecyclerView rView;
    private RecyclerView.LayoutManager rLManager;
    private RecyclerView.Adapter menuAdapter;
    private List<menuData> menu;
    private String type;
    RoomDb dBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select);



        dBase = RoomDb.getInstance(this);

        Intent i = getIntent();
        type = i.getStringExtra("menuType");
        menuType = findViewById(R.id.itemType_ms);
        menuType.setText(type);
        menu = dBase.restaurantDao().getItemsByType(type.toLowerCase());


//        head = findViewById(R.id.head_ms);
//        getLayoutInflater().inflate(R.layout.header_1,head);

        rView = findViewById(R.id.menuView);
        rLManager = new LinearLayoutManager(this);
        rView.setLayoutManager(rLManager);

        menuAdapter = new MenuItemsAdapter(menu_select.this, menu);
        rView.setAdapter(menuAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.head_frame,new headerFragment())
                .commit();
    }
}