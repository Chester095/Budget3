package com.example.budget3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget3.model.AppRepository;
import com.example.budget3.model.Bill;
import com.example.budget3.model.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    AppRepository appRepository;
    private LiveData<List<Bill>> bills;
    private LiveData<List<Movie>> billMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Bill>> getBills() {
        bills = appRepository.getBills();
        return bills;
    }

    public LiveData<List<Movie>> getBillMovies(int billId) {
        billMovies = appRepository.getBillMovies(billId);
        return billMovies;
    }

    //метод чтобы добавлять новый фильм
    public void addNewMovie(Movie movie) {
        appRepository.insertMovie(movie);
    }

    public void updateMovie(Movie movie) {
        appRepository.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        appRepository.deleteMovie(movie);
    }
}
