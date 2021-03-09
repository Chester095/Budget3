package com.example.budget3.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private BillDao billDao;
    private ListItemDao listItemDao;

    private LiveData<List<Bill>> bills;
    private LiveData<List<ListItem>> listItems;

    public AppRepository(Application application) {

        ListItemsDatabase database = ListItemsDatabase.getInstance(application);
        billDao = database.getBillDao();
        listItemDao = database.getListItemDao();
    }

    public LiveData<List<Bill>> getBills() {
        return billDao.getAllBills();
    }

    public LiveData<List<ListItem>> getBillListItems(int billId) {
        return listItemDao.getBillListItems(billId);
    }

    public void insertBill(Bill bill) {
        new InsertBillAsyncTask(billDao).execute(bill);
    }

    public void insertListItem(ListItem listItem) {
        new InsertListItemAsyncTask(listItemDao).execute(listItem);
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

    private static class InsertListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {

        private ListItemDao listItemDao;

        public InsertListItemAsyncTask(ListItemDao listItemDao) {
            this.listItemDao = listItemDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {

            listItemDao.insert(listItems[0]);
            return null;
        }
    }

    public void updateBill(Bill bill) {

        new UpdateBillAsyncTask(billDao).execute(bill);

    }

    public void updateListItem(ListItem listItem) {

        new UpdateListItemAsyncTask(listItemDao).execute(listItem);

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

    private static class UpdateListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {

        private ListItemDao listItemDao;

        public UpdateListItemAsyncTask(ListItemDao listItemDao) {
            this.listItemDao = listItemDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {

            listItemDao.update(listItems[0]);
            return null;
        }
    }

    public void deleteBill(Bill bill) {

        new DeleteBillAsyncTask(billDao).execute(bill);
    }


    public void deleteListItem(ListItem listItem) {
        new DeleteListItemAsyncTask(listItemDao).execute(listItem);
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

    private static class DeleteListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {

        private ListItemDao listItemDao;

        public DeleteListItemAsyncTask(ListItemDao listItemDao) {
            this.listItemDao = listItemDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {

            listItemDao.delete(listItems[0]);
            return null;
        }
    }


}
