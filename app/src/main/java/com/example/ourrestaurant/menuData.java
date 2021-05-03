package com.example.ourrestaurant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Data class for restaurant menu
@Entity(tableName = "table_restaurant_menu")
public class menuData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int ID;
    @ColumnInfo(name = "type")
    String type;
    @ColumnInfo(name = "itemName")
    String itemName;
    @ColumnInfo(name = "price")
    int price;
    @ColumnInfo(name = "foodPic")
    int foodPic;


    public menuData(String itemName, String type, int price, int foodPic){
        this.itemName = itemName;
        this.type = type;
        this.price = price;
        this.foodPic = foodPic;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
