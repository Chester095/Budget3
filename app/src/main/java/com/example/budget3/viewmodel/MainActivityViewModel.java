package com.example.budget3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget3.model.AppRepository;
import com.example.budget3.model.Bill;
import com.example.budget3.model.Operation;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    AppRepository appRepository;
    private LiveData<List<Bill>> bills;
    private LiveData<List<Operation>> billOperations;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Bill>> getBills() {
        bills = appRepository.getBills();
        return bills;
    }

    public LiveData<List<Operation>> getBillOperations(int billId) {
        billOperations = appRepository.getBillOperations(billId);
        return billOperations;
    }

    //метод чтобы добавлять новые операции
    public void addNewOperation(Operation operation) {
        appRepository.insertOperation(operation);
    }
    public void updateOperation(Operation operation) {
        appRepository.updateOperation(operation);
    }
    public void deleteOperation(Operation operation) {
        appRepository.deleteOperation(operation);
    }
}
