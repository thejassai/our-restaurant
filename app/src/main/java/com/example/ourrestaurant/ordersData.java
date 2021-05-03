package com.example.ourrestaurant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ordersTable")
public class ordersData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int ID;
    @ColumnInfo(name = "orderTotal")
    double orderTotal;
    @ColumnInfo(name = "items")
    String items;
    @ColumnInfo(name = "dateTime")
    String dateTime;

    public ordersData(double orderTotal, String items, String dateTime){
        this.orderTotal = orderTotal;
        this.items = items;
        this.dateTime = dateTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
