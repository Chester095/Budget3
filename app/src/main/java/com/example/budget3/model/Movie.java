package com.example.budget3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table", foreignKeys = @ForeignKey(entity = Bill.class,
        parentColumns = "id", childColumns = "bill_id",//связываем таблицы
        onDelete = ForeignKey.CASCADE)) // указываем, что если срока жанра будет удалена, что делать со всеми фильмами (удаляем)
public class Movie extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    private int movieId;
    @ColumnInfo(name = "movie_name")
    private String movieName;
    @ColumnInfo(name = "movie_description")
    private String movieDescription;
    @ColumnInfo(name = "bill_id")
    private int billId;

    @Ignore
    public Movie() {
    }

    public Movie(int movieId, String movieName, String movieDescription, int billId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.billId = billId;
    }

    @Bindable
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
        notifyPropertyChanged(BR.movieId);
    }

    @Bindable
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
        notifyPropertyChanged(BR.movieName);
    }

    @Bindable
    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
        notifyPropertyChanged(BR.movieDescription);
    }

    @Bindable
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }
}
