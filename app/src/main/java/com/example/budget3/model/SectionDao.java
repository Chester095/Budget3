package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SectionDao {

    @Insert
    void insert(Section section);

    @Update
    void update(Section section);

    @Delete
    void delete(Section section);

    @Query("select * from sections_table")
    LiveData<List<Section>> getAllSections();
}
