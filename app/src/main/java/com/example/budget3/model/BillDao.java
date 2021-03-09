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
    void insert(Bill genre);

    @Update
    void update(Bill genre);

    @Delete
    void delete(Bill genre);

    @Query("select * from bills_table")
    LiveData<List<Bill>> getAllGenres();
}
