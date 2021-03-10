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
                    OperationsDatabase.class, "OperationsDB")
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
            operation1.setOperationDescription("The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
            operation1.setBillId(1);

            Operation operation2 = new Operation();
            operation2.setOperationName("Parasite");
            operation2.setOperationDescription("All unemployed, Ki-taek and his family take peculiar interest in the wealthy and glamorous Parks, as they ingratiate themselves into their lives and get entangled in an unexpected incident.");
            operation2.setBillId(1);

            Operation operation3 = new Operation();
            operation3.setOperationName(" Once Upon a Time... in Hollywood");
            operation3.setOperationDescription("A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.");
            operation3.setBillId(1);

            Operation operation4 = new Operation();
            operation4.setOperationName("You");
            operation4.setOperationDescription("A dangerously charming, intensely obsessive young man goes to extreme measures to insert himself into the lives of those he is transfixed by.");
            operation4.setBillId(2);

            Operation operation5 = new Operation();
            operation5.setOperationName("Little Women");
            operation5.setOperationDescription("Jo March reflects back and forth on her life, telling the beloved story of the March sisters - four young women each determined to live life on their own terms.");
            operation5.setBillId(2);

            Operation operation6 = new Operation();
            operation6.setOperationName("Vikings");
            operation6.setOperationDescription("Vikings transports us to the brutal and mysterious world of Ragnar Lothbrok, a Viking warrior and farmer who yearns to explore - and raid - the distant shores across the ocean.");
            operation6.setBillId(2);

            Operation operation7 = new Operation();
            operation7.setOperationName("1917");
            operation7.setOperationDescription("Two young British soldiers during the First World War are given an impossible mission: deliver a message deep in enemy territory that will stop 1,600 men, and one of the soldiers' brothers, from walking straight into a deadly trap.");
            operation7.setBillId(3);

            Operation operation8 = new Operation();
            operation8.setOperationName("The Witcher");
            operation8.setOperationDescription("Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.");
            operation8.setBillId(3);

            Operation operation9 = new Operation();
            operation9.setOperationName("The Outsider");
            operation9.setOperationDescription("Investigators are confounded over an unspeakable crime that's been committed.");
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
