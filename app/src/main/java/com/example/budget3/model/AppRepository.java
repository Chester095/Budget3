package com.example.budget3.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private BillDao billDao;
    private OperationDao operationDao;

    private LiveData<List<Bill>> bills;
    private LiveData<List<Operation>> Operations;

    public AppRepository(Application application) {

        OperationsDatabase database = OperationsDatabase.getInstance(application);
        billDao = database.getBillDao();
        operationDao = database.getOperationDao();
    }

    public LiveData<List<Bill>> getBills() {
        return billDao.getAllBills();
    }

    public LiveData<List<Operation>> getBillOperations(int billId) {
        return operationDao.getBillOperations(billId);
    }

    public void insertBill(Bill bill) {
        new InsertBillAsyncTask(billDao).execute(bill);
    }

    public void insertOperation(Operation operation) {
        new InsertOperationAsyncTask(operationDao).execute(operation);
    }

    private static class InsertBillAsyncTask extends AsyncTask<Bill, Void, Void> {

        private BillDao billDao;

        public InsertBillAsyncTask(BillDao billDao) {
            this.billDao = billDao;
        }

        @Override
        protected Void doInBackground(Bill... bills) {
            billDao.insert(bills[0]);
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

    public void updateBill(Bill bill) {

        new UpdateBillAsyncTask(billDao).execute(bill);

    }

    public void updateOperation(Operation operation) {

        new UpdateOperationAsyncTask(operationDao).execute(operation);

    }

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
