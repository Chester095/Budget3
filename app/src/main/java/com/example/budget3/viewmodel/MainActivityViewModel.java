package com.example.budget3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget3.model.AppRepository;
import com.example.budget3.model.Bill;
import com.example.budget3.model.Operation;
import com.example.budget3.model.Section;
import com.example.budget3.model.SubSection;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    AppRepository appRepository;
    private LiveData<List<Bill>> bills;
    private LiveData<List<Section>> sections;
    private LiveData<List<SubSection>> subsections;
    private LiveData<List<Operation>> billOperations;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<List<Bill>> getBills() {
        bills = appRepository.getBills();
        return bills;
    }
    public LiveData<List<Section>> getSections() {
        sections = appRepository.getSections();
        return sections;
    }
    public LiveData<List<SubSection>> getSubsections() {
        subsections = appRepository.getSubSections();
        return subsections;
    }

    public LiveData<List<Operation>> getBillOperations(int billId) {
        billOperations = appRepository.getBillOperations(billId);
        return billOperations;
    }

    public LiveData<List<Operation>> getSectionOperations(int sectionId) {
        billOperations = appRepository.getSectionOperations(sectionId);
        return billOperations;
    }

    public LiveData<List<Operation>> getSubSectionOperations(int subsectionId) {
        billOperations = appRepository.getBillOperations(subsectionId);
        return billOperations;
    }

    //метод чтобы добавлять новую операцию
    public void addNewOperation(Operation operation) { appRepository.insertOperation(operation); }

    public void updateOperation(Operation operation) {
        appRepository.updateOperation(operation);
    }
    public void deleteOperation(Operation operation) {
        appRepository.deleteOperation(operation);
    }
}
