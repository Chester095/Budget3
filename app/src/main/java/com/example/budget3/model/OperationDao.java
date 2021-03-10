package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OperationDao {

    @Insert
    void insert(Operation operation);

    @Update
    void update(Operation operation);

    @Delete
    void delete(Operation operation);

    @Query("select * from operations_table")
    LiveData<List<Operation>> getAllOperations();

    @Query("select * from operations_table where bill_id==:billId")
    LiveData<List<Operation>> getBillOperations(int billId);

}
