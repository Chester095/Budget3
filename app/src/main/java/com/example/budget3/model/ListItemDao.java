package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListItemDao {

    @Insert
    void insert(ListItem listItem);

    @Update
    void update(ListItem listItem);

    @Delete
    void delete(ListItem listItem);

    @Query("select * from listItems_table")
    LiveData<List<ListItem>> getAllMovies();

    @Query("select * from listItems_table where bill_id==:billId")
    LiveData<List<ListItem>> getBillListItems(int billId);

}
