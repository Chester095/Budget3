package com.example.budget3.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Bill.class, Operation.class}, version = 1)
public abstract class OperationsDatabase extends RoomDatabase {
    private static OperationsDatabase instance;
    public abstract BillDao getBillDao();
    public abstract OperationDao getOperationDao();
    public static synchronized OperationsDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OperationsDatabase.class, "operationsDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private BillDao billDao;
        private OperationDao operationDao;

        public InitialDataAsyncTask(OperationsDatabase database) {
            billDao = database.getBillDao();
            operationDao = database.getOperationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bill comedyBill = new Bill();
            comedyBill.setBillName("Тинькофф");

            Bill romanceBill = new Bill();
            romanceBill.setBillName("Сбербанк");

            Bill dramaBill = new Bill();
            dramaBill.setBillName("Газпромбанк");

            billDao.insert(comedyBill);
            billDao.insert(romanceBill);
            billDao.insert(dramaBill);


            Operation operation1 = new Operation();
            operation1.setOperationName("Bad Boys for Life");
            operation1.setOperationDescription("The Bad Boys Mike Lowrey and Marcus.");
            operation1.setOperationAmount(15351);
            operation1.setOperationAmountD(15.5);
            operation1.setBillId(1);

            Operation operation2 = new Operation();
            operation2.setOperationName("Parasite");
            operation2.setOperationDescription("All unemployed, Ki-taek and his fam.");
            operation2.setOperationAmount(81651);
            operation2.setOperationAmountD(15.5);
            operation2.setBillId(1);

            Operation operation3 = new Operation();
            operation3.setOperationName(" Once Upon a Time... in Hollywood");
            operation3.setOperationDescription("A faded television actor and his st.");
            operation3.setOperationAmount(338457);
            operation3.setOperationAmountD(15.5);
            operation3.setBillId(1);

            Operation operation4 = new Operation();
            operation4.setOperationName("You");
            operation4.setOperationDescription("A dangerously charming, intensely o.");
            operation4.setOperationAmount(32151);
            operation4.setOperationAmountD(15.5);
            operation4.setBillId(2);

            Operation operation5 = new Operation();
            operation5.setOperationName("Little Women");
            operation5.setOperationDescription("Jo March reflects back and forth on.");
            operation5.setOperationAmount(87954);
            operation5.setOperationAmountD(15.5);
            operation5.setBillId(2);

            Operation operation6 = new Operation();
            operation6.setOperationName("Vikings");
            operation6.setOperationDescription("Vikings transports us to the brutal.");
            operation6.setOperationAmount(3453);
            operation6.setOperationAmountD(15.5);
            operation6.setBillId(2);

            Operation operation7 = new Operation();
            operation7.setOperationName("1917");
            operation7.setOperationDescription("Two young British soldiers during t.");
            operation7.setOperationAmount(456);
            operation7.setOperationAmountD(15.5);
            operation7.setBillId(3);

            Operation operation8 = new Operation();
            operation8.setOperationName("The Witcher");
            operation8.setOperationDescription("Geralt of Rivia, a solitary monster.");
            operation8.setOperationAmount(3);
            operation8.setOperationAmountD(15.5);
            operation8.setBillId(3);

            Operation operation9 = new Operation();
            operation9.setOperationName("The Outsider");
            operation9.setOperationDescription("Investigators are confounded over a.");
            operation9.setOperationAmount(5678);
            operation9.setOperationAmountD(15.5);
            operation9.setBillId(3);

            operationDao.insert(operation1);
            operationDao.insert(operation2);
            operationDao.insert(operation3);
            operationDao.insert(operation4);
            operationDao.insert(operation5);
            operationDao.insert(operation6);
            operationDao.insert(operation7);
            operationDao.insert(operation8);
            operationDao.insert(operation9);

            return null;
        }
    }

}
