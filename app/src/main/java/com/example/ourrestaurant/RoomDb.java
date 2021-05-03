package com.example.ourrestaurant;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//db entities
@Database(entities = {menuData.class, cartData.class, ordersData.class}, version = 6, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    private static RoomDb dBase;

    private static String DATABASE_NAME = "restaurantDatabase";

    public synchronized static RoomDb getInstance(Context context){
        if (dBase == null){

            dBase = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //return db
        return dBase;
    }
    //Dao file
    public abstract RestaurantDao restaurantDao();
    public abstract cartDao cartDao();
    public abstract ordersDao ordersDao();
}
