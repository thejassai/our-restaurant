package com.example.ourrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<menuData> menu;
    LinearLayout head;
    ConstraintLayout cl_start, cl_bf, cl_mc, cl_desserts, cl_drinks;
    RoomDb resDatabase;
    Button myOrders;

    private View.OnClickListener showMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, menu_select.class);
            i.putExtra("menuType",v.getTag().toString());
            startActivity(i);

            //Toast.makeText(MainActivity.this,v.getTag().toString(),Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //head = findViewById(R.id.head_main);
        //getLayoutInflater().inflate(R.layout.header_1,head);

        resDatabase = RoomDb.getInstance(this);

//        resDatabase.restaurantDao().insert(new menuData("Chilli Chicken","starters",24, R.drawable.chilli_chicken));
//        resDatabase.restaurantDao().insert(new menuData("Chicken wings","starters",21, R.drawable.staters1));
//        resDatabase.restaurantDao().insert(new menuData("Chicken Nuggets","starters",15, R.drawable.staters2));
//        resDatabase.restaurantDao().insert(new menuData("Barbeque Chicken Wings","starters",16, R.drawable.staters3));
//        resDatabase.restaurantDao().insert(new menuData("Chicken Sandwich","breakfast",26, R.drawable.chicken_sandwich));
//        resDatabase.restaurantDao().insert(new menuData("Egg Sandwich","breakfast",32, R.drawable.breakfast1));
//        resDatabase.restaurantDao().insert(new menuData("Cereal","breakfast",24, R.drawable.breakfast2));
//        resDatabase.restaurantDao().insert(new menuData("Egg plant sandwich","breakfast",18, R.drawable.breakfast4));
//        resDatabase.restaurantDao().insert(new menuData("Omlete","breakfast",12, R.drawable.breakfast3));
//        resDatabase.restaurantDao().insert(new menuData("Chicken Biryani","main course", 17,R.drawable.chicken_biryani));
//        resDatabase.restaurantDao().insert(new menuData("Mushroom Curry","main course", 16,R.drawable.main1));
//        resDatabase.restaurantDao().insert(new menuData("Potato Pulav","main course", 19,R.drawable.main2));
//        resDatabase.restaurantDao().insert(new menuData("Noodles","main course", 19,R.drawable.main3));
//        resDatabase.restaurantDao().insert(new menuData("Chocolate mousse","desserts",8, R.drawable.chocolate_mousse));
//        resDatabase.restaurantDao().insert(new menuData("Cheesecake","desserts",13, R.drawable.desserts1));
//        resDatabase.restaurantDao().insert(new menuData("Caramel chocolate Icecream","desserts",12, R.drawable.desserts2));
//        resDatabase.restaurantDao().insert(new menuData("Milk love bytes","desserts",15, R.drawable.desserts3));
//        resDatabase.restaurantDao().insert(new menuData("Genoise Cake","desserts",21, R.drawable.desserts4));
//        resDatabase.restaurantDao().insert(new menuData("Coke", "drinks", 6, R.drawable.coke_can));
//        resDatabase.restaurantDao().insert(new menuData("Lemonade", "drinks", 7, R.drawable.water1));
//        resDatabase.restaurantDao().insert(new menuData("Whiskey", "drinks", 8, R.drawable.water2));
//        resDatabase.restaurantDao().insert(new menuData("Mango juice", "drinks", 4, R.drawable.water3));
//        resDatabase.restaurantDao().insert(new menuData("Apple Mocktail", "drinks", 5, R.drawable.water4));


        cl_start = findViewById(R.id.starter_main);
        cl_bf = findViewById(R.id.break_main);
        cl_mc = findViewById(R.id.mcourse_main);
        cl_desserts = findViewById(R.id.desserts_main);
        cl_drinks = findViewById(R.id.drinks_main);

        cl_start.setOnClickListener(showMenu);
        cl_bf.setOnClickListener(showMenu);
        cl_mc.setOnClickListener(showMenu);
        cl_drinks.setOnClickListener(showMenu);
        cl_desserts.setOnClickListener(showMenu);

        myOrders = findViewById(R.id.btn_orders);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, Orders.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.head_frame,new headerFragment())
                .commit();
    }
}