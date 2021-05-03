package com.example.ourrestaurant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ordersDao {
    @Insert(onConflict = REPLACE)
    void insert(ordersData ordersData);

    @Query("select * from ordersTable")
    List<ordersData> getAll();
}
