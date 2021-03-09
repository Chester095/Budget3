package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


//Жанры Фильма
@Entity(tableName = "bills_table")
public class Bill extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "bill_name")
    private String billName;

    @Ignore
    public Bill() {
    }

    public Bill(int id, String billName) {
        this.id = id;
        this.billName = billName;
    }

    @Bindable //для индентификации
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
        notifyPropertyChanged(BR.billName);
    }

    @Override
    public String toString() {
        return this.billName;
    }
}
