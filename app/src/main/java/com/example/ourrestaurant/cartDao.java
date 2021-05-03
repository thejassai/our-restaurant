package com.example.ourrestaurant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface cartDao {
    @Insert(onConflict = REPLACE)
    void insert(cartData cartData);

    @Query("select * from cartTable")
    List<cartData> getAll();

    @Query("select * from cartTable where ID=:cId")
    cartData getCartItemById(int cId);

    @Query("update cartTable set itemQty=:qty where ID=:cId")
    void update(int cId,int qty);

    @Query(("delete from cartTable where ID=:cId"))
    void delete(int cId);
}
