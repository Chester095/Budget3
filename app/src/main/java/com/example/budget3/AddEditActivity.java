package com.example.budget3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.budget3.databinding.ActivityAddEditBinding;
import com.example.budget3.model.Movie;

public class AddEditActivity extends AppCompatActivity {

    private Movie movie;

    public static final String MOVIE_ID = "movieId";
    public static final String MOVIE_NAME = "movieName";
    public static final String MOVIE_DESCRIPTION = "movieDescription";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers addEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        movie = new Movie();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setMovie(movie);
        addEditActivityClickHandlers = new AddEditActivityClickHandlers(this);
        activityAddEditBinding.setClickHandlers(addEditActivityClickHandlers);

        Intent intent = getIntent();

        if (intent.hasExtra(MOVIE_ID)) {
            setTitle("Edit movie");

            movie.setMovieName(intent.getStringExtra(MOVIE_NAME));
            movie.setMovieDescription(intent.getStringExtra(MOVIE_DESCRIPTION));
        } else {
            setTitle("Add movie");
        }

    }

    public class AddEditActivityClickHandlers {

        Context context;

        public AddEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onOkButtonClicked(View view) {

            if (movie.getMovieName() == null) {

                Toast.makeText(context, "Please input the mame", Toast.LENGTH_SHORT).show();

            } else {

                Intent intent = new Intent();
                intent.putExtra(MOVIE_NAME, movie.getMovieName());
                intent.putExtra(MOVIE_DESCRIPTION, movie.getMovieDescription());
                setResult(RESULT_OK, intent);
                finish();

            }

        }
    }
}
