package com.example.ourrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cartActivity extends AppCompatActivity implements restInterface{

    RecyclerView rview;
    RoomDb restDb;
    List<cartData> catData;
    //List<menuData> mData;
    List<cartCart> ccData;
    ImageView btn_closeCart;
    TextView sTotal, taxes, delFee, grandTot;
    int subTot = 0;
    Double taxS = 0.0, tot;
    Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btn_closeCart = findViewById(R.id.btn_cartClose);
        btn_closeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void deleteItem(int itemId){
        restDb.cartDao().delete(itemId);
    }
    public menuData getMenuItem(int itemId){
        menuData p = restDb.restaurantDao().getItemById(itemId);
        return p;
    }

    @Override
    protected void onResume() {
        super.onResume();
        restDb = RoomDb.getInstance(this);
        catData = restDb.cartDao().getAll();
        rview = findViewById(R.id.rView_cart);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(new CartAdapter(cartActivity.this,catData));
        setBillDet();
    }

    public void setBillDet(){
        sTotal = findViewById(R.id.stotal_cA);
        taxes = findViewById(R.id.taxes_cA);
        delFee = findViewById(R.id.delivery_cA);
        grandTot = findViewById(R.id.tot_cA);
        btn_continue = findViewById(R.id.btnContinue_cA);
        subTot=0;
        for (cartData cd : catData){
            subTot+=restDb.restaurantDao().getItemById(cd.cartItemID).price*cd.itemQty;
        }
        taxS = subTot*0.28;
        taxS = Math.round(taxS*100)/100.0;
        tot = subTot+taxS+2.99;
        tot = Math.round(tot*100)/100.0;
        sTotal.setText("$ "+Integer.toString(subTot));
        taxes.setText("$ "+Double.toString(taxS));
        delFee.setText("$ 2.99");
        grandTot.setText("$ "+Double.toString(tot));
        btn_continue.setText("Order\t\t\t\t\t$ "+Double.toString(tot));
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, Integer> ord = new HashMap<>();
                int n;
                for (cartData cD : catData) {
                    if(!ord.containsKey(cD.cartItemID)){
                        ord.put(cD.cartItemID,cD.itemQty);
                    }
                    else {
                        n = ord.get(cD.cartItemID);
                        ord.put(cD.cartItemID,n+cD.itemQty);
                    }
                }
                String items = "";
                for (Map.Entry<Integer, Integer> x:ord.entrySet()){
                    items+=x.getKey().toString()+":"+x.getValue()+",";
                }
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dTime = new SimpleDateFormat(("MMMM d''yy HH:mm"));
                String dateTime = dTime.format(cal.getTime());
                restDb.ordersDao().insert(new ordersData(tot,items,dateTime));
                Intent i = new Intent(cartActivity.this, Orders.class);
                startActivity(i);
            }
        });
    }
}