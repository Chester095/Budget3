package com.example.budget3.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private BillDao genreDao;
    private MovieDao movieDao;

    private LiveData<List<Bill>> genres;
    private LiveData<List<Movie>> movies;

    public AppRepository(Application application) {

        MoviesDatabase database = MoviesDatabase.getInstance(application);
        genreDao = database.getGenreDao();
        movieDao = database.getMovieDao();
    }

    public LiveData<List<Bill>> getGenres() {
        return genreDao.getAllGenres();
    }

    public LiveData<List<Movie>> getGenreMovies(int genreId) {
        return movieDao.getGenreMovies(genreId);
    }

    public void insertGenre(Bill genre) {
        new InsertGenreAsyncTask(genreDao).execute(genre);
    }

    public void insertMovie(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    private static class InsertGenreAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao genreDao;

        public InsertGenreAsyncTask(BillDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Bill... genres) {
            genreDao.insert(genres[0]);
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

    public void updateGenre(Bill genre) {

        new UpdateGenreAsyncTask(genreDao).execute(genre);

    }

    public void updateMovie(Movie movie) {

        new UpdateMovieAsyncTask(movieDao).execute(movie);

    }

    private static class UpdateGenreAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao genreDao;

        public UpdateGenreAsyncTask(BillDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Bill... genres) {
            genreDao.update(genres[0]);
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

    public void deleteGenre(Bill genre) {

        new DeleteGenreAsyncTask(genreDao).execute(genre);
    }


    public void deleteMovie(Movie movie) {
        new DeleteMovieAsyncTask(movieDao).execute(movie);
    }

    private static class DeleteGenreAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao genreDao;

        public DeleteGenreAsyncTask(BillDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Bill... genres) {
            genreDao.delete(genres[0]);
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
