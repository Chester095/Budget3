package com.example.budget3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("select * from movies_table")
    LiveData<List<Movie>> getAllMovies();

    @Query("select * from movies_table where bill_id==:billId")
    LiveData<List<Movie>> getGenreMovies(int billId);

}
