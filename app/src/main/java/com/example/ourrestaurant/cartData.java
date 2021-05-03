package com.example.ourrestaurant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cartTable")
public class cartData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "cartItemId")
    int cartItemID;
    @ColumnInfo(name = "itemQty")
    int itemQty;

    public cartData(int cartItemID, int itemQty){
        this.cartItemID=cartItemID;
        this.itemQty = itemQty;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
