package com.example.budget3.model;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AppRepository {

    private BillDao billDao;
    private SectionDao sectionDao;
    private SubSectionDao subSectionDao;
    private OperationDao operationDao;

    private LiveData<List<Bill>> bills;
    private LiveData<List<Section>> sections;
    private LiveData<List<SubSection>> subSections;
    private LiveData<List<Operation>> Operations;

    public AppRepository(Application application) {

        OperationsDatabase database = OperationsDatabase.getInstance(application);
        billDao = database.getBillDao();
        sectionDao = database.getSectionDao();
        subSectionDao = database.getSubSectionDao();
        operationDao = database.getOperationDao();
    }

    public LiveData<List<Bill>> getBills() {
        return billDao.getAllBills();
    }
    public LiveData<List<Section>> getSections() { return sectionDao.getAllSections(); }
    public LiveData<List<SubSection>> getSubSections() { return subSectionDao.getAllSubSections(); }

    public LiveData<List<Operation>> getBillOperations(int billId) { return operationDao.getBillOperations(billId); }
    public LiveData<List<Operation>> getSectionOperations(int sectionId) { return operationDao.getBillOperations(sectionId); }
    public LiveData<List<Operation>> getSubSectionOperations(int subSectionId) { return operationDao.getBillOperations(subSectionId); }

    public void insertBill(Bill bill) { new InsertBillAsyncTask(billDao).execute(bill); }
    public void insertSection(Section section) { new InsertSectionAsyncTask(sectionDao).execute(section); }
    public void insertSubSection(SubSection subSection) { new InsertSubSectionAsyncTask(subSectionDao).execute(subSection); }

    public void insertOperation(Operation operation) { new InsertOperationAsyncTask(operationDao).execute(operation); }
//    public void insertOperation(Operation operation) { new InsertSectionAsyncTask(operationDao).execute(operation); }
//    public void insertOperation(Operation operation) { new InsertOperationAsyncTask(operationDao).execute(operation); }

    private static class InsertBillAsyncTask extends AsyncTask<Bill, Void, Void> {
        private BillDao billDao;
        public InsertBillAsyncTask(BillDao billDao) { this.billDao = billDao; }
        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.insert(bills[0]);
            return null;
        }
    }

    private static class InsertSectionAsyncTask extends AsyncTask<Section, Void, Void> {
        private SectionDao sectionDao;
        public InsertSectionAsyncTask(SectionDao sectionDao) { this.sectionDao = sectionDao; }
        @Override
        protected Void doInBackground(Section... sections) {
            sectionDao.insert(sections[0]);
            return null;
        }
    }

    private static class InsertSubSectionAsyncTask extends AsyncTask<SubSection, Void, Void> {
        private SubSectionDao subSectionDao;
        public InsertSubSectionAsyncTask(SubSectionDao subSectionDao) { this.subSectionDao = subSectionDao; }
        @Override
        protected Void doInBackground(SubSection... subSections) {
            subSectionDao.insert(subSections[0]);
            return null;
        }
    }

    private static class InsertOperationAsyncTask extends AsyncTask<Operation, Void, Void> {
        private OperationDao operationDao;
        public InsertOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }
        @Override
        protected Void doInBackground(Operation... operations) {
            operationDao.insert(operations[0]);
            return null;
        }
    }

    public void updateBill(Bill bill) { new UpdateBillAsyncTask(billDao).execute(bill); }
    public void updateSection(Section section) { new UpdateSectionAsyncTask(sectionDao).execute(section); }
    public void updateSubSection(SubSection subSection) { new UpdateSubSectionAsyncTask(subSectionDao).execute(subSection); }

    public void updateOperation(Operation operation) { new UpdateOperationAsyncTask(operationDao).execute(operation); }

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

    private static class UpdateSectionAsyncTask extends AsyncTask<Section, Void, Void> {
        private SectionDao sectionDao;
        public UpdateSectionAsyncTask(SectionDao sectionDao) {
            this.sectionDao = sectionDao;
        }
        @Override
        protected Void doInBackground(Section... sections) {
            sectionDao.update(sections[0]);
            return null;
        }
    }
    private static class UpdateSubSectionAsyncTask extends AsyncTask<SubSection, Void, Void> {
        private SubSectionDao subSectionDao;
        public UpdateSubSectionAsyncTask(SubSectionDao subSectionDao) {
            this.subSectionDao = subSectionDao;
        }
        @Override
        protected Void doInBackground(SubSection... subSections) {
            subSectionDao.update(subSections[0]);
            return null;
        }
    }

    private static class UpdateOperationAsyncTask extends AsyncTask<Operation, Void, Void> {
        private OperationDao operationDao;
        public UpdateOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }
        @Override
        protected Void doInBackground(Operation... operations) {
            operationDao.update(operations[0]);
            return null;
        }
    }

    public void deleteBill(Bill bill) {
        new DeleteBillAsyncTask(billDao).execute(bill);
    }
    public void deleteSection(Section section) {
        new DeleteSectionAsyncTask(sectionDao).execute(section);
    }
    public void deleteSubSection(SubSection subSection) {
        new DeleteSubSectionAsyncTask(subSectionDao).execute(subSection);
    }

    public void deleteOperation(Operation operation) {
        new DeleteOperationAsyncTask(operationDao).execute(operation);
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

    private static class DeleteSectionAsyncTask extends AsyncTask<Section, Void, Void> {
        private SectionDao sectionDao;
        public DeleteSectionAsyncTask(SectionDao sectionDao) {
            this.sectionDao = sectionDao;
        }
        @Override
        protected Void doInBackground(Section... sections) {
            sectionDao.delete(sections[0]);
            return null;
        }
    }

    private static class DeleteSubSectionAsyncTask extends AsyncTask<SubSection, Void, Void> {
        private SubSectionDao subSectionDao;
        public DeleteSubSectionAsyncTask(SubSectionDao subSectionDao) {
            this.subSectionDao = subSectionDao;
        }
        @Override
        protected Void doInBackground(SubSection... subSections) {
            subSectionDao.delete(subSections[0]);
            return null;
        }
    }

    private static class DeleteOperationAsyncTask extends AsyncTask<Operation, Void, Void> {
        private OperationDao operationDao;
        public DeleteOperationAsyncTask(OperationDao operationDao) {
            this.operationDao = operationDao;
        }
        @Override
        protected Void doInBackground(Operation... operations) {
            operationDao.delete(operations[0]);
            return null;
        }
    }


}
