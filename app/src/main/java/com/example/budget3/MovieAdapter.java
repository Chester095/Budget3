package com.example.budget3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget3.databinding.MovieListItemBinding;
import com.example.budget3.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<com.example.budget3.MovieAdapter.MovieViewHolder> {
    //                                <com.android.uraall.myfavoritemovies.MovieAdapter.MovieViewHolder>

//    type="com.example.budget3.MainActivity.MainActivityClickHandlers" />
//               uraall.myfavoritemovies.MainActivity.MainActivityClickHandlers

    private OnItemClickListener onItemClickListener;
    private ArrayList<Movie> movieArrayList = new ArrayList<>();


    //код который накачивает данными
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.movie_list_item,
                parent, false
        );

        return new MovieViewHolder(movieListItemBinding);
    }


    //получаем объект
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        //передаём объект в разметку
        holder.movieListItemBinding.setMovie(movie);
    }


    //должен вернуть размер ArrayList
    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //можем получить позицию кликнутого элемента
                    int position = getAdapterPosition();
                    //делаем проверку
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(movieArrayList.get(position));
                    }
                }
            });
        }
    }

    //для управления кликом событием
    public interface OnItemClickListener {
        //должны получить объект  movie
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
        notifyDataSetChanged(); //для обновления RecycleView
    }
}
