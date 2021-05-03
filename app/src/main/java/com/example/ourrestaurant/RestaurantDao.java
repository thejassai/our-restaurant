package com.example.ourrestaurant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RestaurantDao {
    @Insert(onConflict = REPLACE)
    void insert(menuData menuData);

    @Query("select * from table_restaurant_menu")
    List<menuData> getAll();

    @Query("select * from table_restaurant_menu where type = :type")
    List<menuData> getItemsByType(String type);

    @Query("select * from table_restaurant_menu where ID= :id")
    menuData getItemById(int id);
}
