package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubSectionDao {

    @Insert
    void insert(SubSection subsection);

    @Update
    void update(SubSection subsection);

    @Delete
    void delete(SubSection subsection);

    @Query("select * from subSections_table")
    LiveData<List<SubSection>> getAllSubSections();
}
