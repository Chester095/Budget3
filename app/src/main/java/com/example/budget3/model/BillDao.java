package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BillDao {

    @Insert
    void insert(Bill bill);

    @Update
    void update(Bill bill);

    @Delete
    void delete(Bill bill);

    @Query("select * from bills_table")
    LiveData<List<Bill>> getAllBills();
}
