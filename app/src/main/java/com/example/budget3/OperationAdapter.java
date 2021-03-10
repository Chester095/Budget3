package com.example.budget3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget3.databinding.OperationListItemBinding;
import com.example.budget3.model.Operation;

import java.util.ArrayList;

public class OperationAdapter extends RecyclerView.Adapter<com.example.budget3.OperationAdapter.OperationViewHolder> {

    private OnItemClickListener onItemClickListener;
    private ArrayList<Operation> operationArrayList = new ArrayList<>();


    //код который накачивает данными
    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        OperationListItemBinding OperationListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.operation_list_item,
                parent, false
        );

        return new OperationViewHolder(OperationListItemBinding);
    }


    //получаем объект
    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder holder, int position) {
        Operation operation = operationArrayList.get(position);
        //передаём объект в разметку
        holder.OperationListItemBinding.setOperation(operation);
    }


    //должен вернуть размер ArrayList
    @Override
    public int getItemCount() {
        return operationArrayList.size();
    }

    class OperationViewHolder extends RecyclerView.ViewHolder{

        OperationListItemBinding OperationListItemBinding;

        public OperationViewHolder(@NonNull OperationListItemBinding OperationListItemBinding) {
            super(OperationListItemBinding.getRoot());
            this.OperationListItemBinding = OperationListItemBinding;
            OperationListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //можем получить позицию кликнутого элемента
                    int position = getAdapterPosition();
                    //делаем проверку
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(operationArrayList.get(position));
                    }
                }
            });
        }
    }

    //для управления кликом событием
    public interface OnItemClickListener {
        //должны получить объект  operation
        void onItemClick(Operation operation);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOperationArrayList(ArrayList<Operation> operationArrayList) {
        this.operationArrayList = operationArrayList;
        notifyDataSetChanged(); //для обновления RecycleView
    }
}
