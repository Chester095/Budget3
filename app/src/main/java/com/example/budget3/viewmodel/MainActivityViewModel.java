package com.example.budget3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget3.model.AppRepository;
import com.example.budget3.model.Bill;
import com.example.budget3.model.ListItem;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    AppRepository appRepository;
    private LiveData<List<Bill>> bills;
    private LiveData<List<ListItem>> billListItems;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Bill>> getBills() {
        bills = appRepository.getBills();
        return bills;
    }

    public LiveData<List<ListItem>> getBillListItems(int billId) {
        billListItems = appRepository.getBillListItems(billId);
        return billListItems;
    }

    //метод чтобы добавлять новый фильм
    public void addNewListItem(ListItem listItem) {
        appRepository.insertListItem(listItem);
    }

    public void updateListItem(ListItem listItem) {
        appRepository.updateListItem(listItem);
    }

    public void deleteListItem(ListItem listItem) {
        appRepository.deleteListItem(listItem);
    }
}
