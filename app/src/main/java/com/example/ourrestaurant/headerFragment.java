package com.example.ourrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class headerFragment extends Fragment {
    Button btn_cart;
    RoomDb restDatabase;
    ImageView btn_home;
    List<cartData> cartDataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.header_1,container,false);

        restDatabase = RoomDb.getInstance(getActivity());


        cartDataList = restDatabase.cartDao().getAll();

        btn_cart = view.findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Working",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(),cartActivity.class);
                startActivity(i);
            }
        });

        btn_cart.setText("CART("+cartDataList.size()+")");

        btn_home = view.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });

        return view;



    }
}
