package com.example.budget3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget3.databinding.ActivityMainBinding;
import com.example.budget3.model.Genre;
import com.example.budget3.model.Movie;
import com.example.budget3.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    private Genre selectedGenre;
    private ArrayList<Genre> genreArrayList;
    private ArrayList<Movie> movieArrayList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private int selectedMovieId;

    public static final int ADD_MOVIE_REQUEST_CODE = 111;
    public static final int EDIT_MOVIE_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        clickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(clickHandlers);

        mainActivityViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {

                genreArrayList = (ArrayList<Genre>) genres;
                for (Genre genre : genres) {
                    Log.d("MyTAG", genre.getGenreName());
                }

                showInSpinner();
            }
        });


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void showInSpinner() {

        ArrayAdapter<Genre> genreArrayAdapter = new ArrayAdapter<Genre>(this,
                R.layout.spinner_item, genreArrayList);
        genreArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(genreArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view) {

//            Toast.makeText(MainActivity.this, "Button is clicked!",
//                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(com.example.budget3.MainActivity.this, com.example.budget3.AddEditActivity.class);
            startActivityForResult(intent, ADD_MOVIE_REQUEST_CODE);

        }

        //для получения выбранного жанра из выпадающего списка
        //метод для спинера. это стандартный код для спинера
        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {

            selectedGenre = (Genre) parent.getItemAtPosition(position);

            String message = "id is " + selectedGenre.getId() +
                    "\n name is " + selectedGenre.getGenreName();

//            Toast.makeText(parent.getContext(), message, Toast.LENGTH_SHORT).show();

            loadGenreMoviesInArrayList(selectedGenre.getId());
        }
    }

    //загружает список фильмов определённого жанра
    private void loadGenreMoviesInArrayList(int genreID) {
        mainActivityViewModel.getGenreMovies(genreID).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieArrayList = (ArrayList<Movie>) movies;
                loadRecyclerView();
            }
        });
    }

    //для загрузки RecycledView
    private void loadRecyclerView() {

        recyclerView = activityMainBinding.secondaryLayout.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter();
        movieAdapter.setMovieArrayList(movieArrayList);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                selectedMovieId = movie.getMovieId();
                Intent intent = new Intent(com.example.budget3.MainActivity.this,
                        com.example.budget3.AddEditActivity.class);
                intent.putExtra(com.example.budget3.AddEditActivity.MOVIE_ID, selectedMovieId);
                intent.putExtra(com.example.budget3.AddEditActivity.MOVIE_NAME, movie.getMovieName());
                intent.putExtra(com.example.budget3.AddEditActivity.MOVIE_DESCRIPTION, movie.getMovieDescription());
                startActivityForResult(intent, EDIT_MOVIE_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {

                Movie movieToDelete = movieArrayList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteMovie(movieToDelete);

            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedGenreId = selectedGenre.getId();

        if (requestCode == ADD_MOVIE_REQUEST_CODE && resultCode == RESULT_OK) {

            Movie movie = new Movie();
            movie.setGenreId(selectedGenreId);
            movie.setMovieName(data.getStringExtra(com.example.budget3.AddEditActivity.MOVIE_NAME));
            movie.setMovieDescription(data.getStringExtra(com.example.budget3.AddEditActivity.MOVIE_DESCRIPTION));

            mainActivityViewModel.addNewMovie(movie);

        } else if (requestCode == EDIT_MOVIE_REQUEST_CODE && resultCode == RESULT_OK) {

            Movie movie = new Movie();
            movie.setMovieId(selectedMovieId);
            movie.setGenreId(selectedGenreId);
            movie.setMovieName(data.getStringExtra(com.example.budget3.AddEditActivity.MOVIE_NAME));
            movie.setMovieDescription(data.getStringExtra(com.example.budget3.AddEditActivity.MOVIE_DESCRIPTION));

            mainActivityViewModel.updateMovie(movie);

        }
    }
}
