package com.example.budget3.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private BillDao billDao;
    private MovieDao movieDao;

    private LiveData<List<Bill>> bills;
    private LiveData<List<Movie>> movies;

    public AppRepository(Application application) {

        MoviesDatabase database = MoviesDatabase.getInstance(application);
        billDao = database.getBillDao();
        movieDao = database.getMovieDao();
    }

    public LiveData<List<Bill>> getBills() {
        return billDao.getAllBills();
    }

    public LiveData<List<Movie>> getBillMovies(int billId) {
        return movieDao.getBillMovies(billId);
    }

    public void insertBill(Bill bill) {
        new InsertBillAsyncTask(billDao).execute(bill);
    }

    public void insertMovie(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    private static class InsertBillAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao billDao;

        public InsertBillAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.insert(bills[0]);
            return null;
        }
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao movieDao;

        public InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.insert(movies[0]);
            return null;
        }
    }

    public void updateBill(Bill bill) {

        new UpdateBillAsyncTask(billDao).execute(bill);

    }

    public void updateMovie(Movie movie) {

        new UpdateMovieAsyncTask(movieDao).execute(movie);

    }

    private static class UpdateBillAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao billDao;

        public UpdateBillAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.update(bills[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao movieDao;

        public UpdateMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.update(movies[0]);
            return null;
        }
    }

    public void deleteBill(Bill bill) {

        new DeleteBillAsyncTask(billDao).execute(bill);
    }


    public void deleteMovie(Movie movie) {
        new DeleteMovieAsyncTask(movieDao).execute(movie);
    }

    private static class DeleteBillAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao billDao;

        public DeleteBillAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.delete(bills[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao movieDao;

        public DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {

            movieDao.delete(movies[0]);
            return null;
        }
    }


}
