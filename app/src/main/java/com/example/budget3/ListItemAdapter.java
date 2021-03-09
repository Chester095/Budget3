package com.example.budget3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget3.databinding.ListitemListItemBinding;
import com.example.budget3.databinding.ListitemListItemBinding;
import com.example.budget3.model.ListItem;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    private OnItemClickListener onItemClickListener;
    private ArrayList<ListItem> listItemArrayList = new ArrayList<>();


    //код который накачивает данными
    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListitemListItemBinding listitemListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.listitem_list_item,
                parent, false
        );

        return new ListItemViewHolder(listitemListItemBinding);
    }


    //получаем объект
    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ListItem listItem = listItemArrayList.get(position);
        //передаём объект в разметку
        holder.movieListItemBinding.setListItem(listItem);
    }


    //должен вернуть размер ArrayList
    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder{

        ListitemListItemBinding movieListItemBinding;

        public ListItemViewHolder(@NonNull ListitemListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //можем получить позицию кликнутого элемента
                    int position = getAdapterPosition();
                    //делаем проверку
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(listItemArrayList.get(position));
                    }
                }
            });
        }
    }

    //для управления кликом событием
    public interface OnItemClickListener {
        //должны получить объект  listItem
        void onItemClick(ListItem listItem);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setListItemArrayList(ArrayList<ListItem> listItemArrayList) {
        this.listItemArrayList = listItemArrayList;
        notifyDataSetChanged(); //для обновления RecycleView
    }
}
