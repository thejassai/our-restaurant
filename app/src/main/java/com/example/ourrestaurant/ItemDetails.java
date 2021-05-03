package com.example.ourrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.IDN;

public class ItemDetails extends AppCompatActivity {
    TextView test, qty;
    ImageView foodPic, btn_minus, btn_plus;
    ScrollView scrollView;
    Button btn_toCart;
    RoomDb restDatabase;
    boolean update;
    menuData x;
    cartData cD;
    int cQty;
    String btnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        scrollView = findViewById(R.id.itemScroll_idet);
        View disView = getLayoutInflater().inflate(R.layout.item_display,scrollView);
        btn_plus = disView.findViewById(R.id.btn_plusItem);
        btn_minus = disView.findViewById(R.id.btn_minusItem);
        qty = disView.findViewById(R.id.qty_iDis);
        btn_toCart = findViewById(R.id.btn_toCart);

        restDatabase = RoomDb.getInstance(this);
        Intent i = getIntent();
        update = i.getBooleanExtra("update",false);
        if(!update){
            x = (menuData) i.getSerializableExtra("itemData");
            btnTxt = "+\t\tAdd To Cart\t\tCA$";
            btn_toCart.setText(btnTxt+Integer.toString(x.price));
        }
        else {
            cD=(cartData) i.getSerializableExtra("cartData");
            x = restDatabase.restaurantDao().getItemById(cD.cartItemID);
            cQty = cD.itemQty;
            qty.setText(Integer.toString(cQty));
            btnTxt = "+\t\tUpdate Cart\t\tCA$";
            btn_toCart.setText(btnTxt+Integer.toString(x.price*cQty));
        }

        //Toast.makeText(ItemDetails.this," fhbriehfowiejoi"+x.itemName, Toast.LENGTH_SHORT).show();

        foodPic = disView.findViewById(R.id.foodImg_idet);
        foodPic.setImageResource(x.foodPic);

        test = disView.findViewById(R.id.test_1);
        test.setText(x.itemName);

        btn_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!update){
                    restDatabase.cartDao().insert(new cartData(x.ID, Integer.parseInt(qty.getText().toString())));

                }
                else {
                    restDatabase.cartDao().update(cD.getID(),Integer.parseInt(qty.getText().toString()));
                }

                ItemDetails.super.onBackPressed();
            }
        });



        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer iQty = Integer.parseInt(qty.getText().toString());
                iQty++;
                qty.setText(Integer.toString(iQty));
                btn_toCart.setText(btnTxt+x.price*iQty);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer iQty = Integer.parseInt(qty.getText().toString());
                if(iQty>1){
                    iQty--;
                    qty.setText(Integer.toString(iQty));
                    btn_toCart.setText(btnTxt+x.price*iQty);
                }
            }
        });


    }
}