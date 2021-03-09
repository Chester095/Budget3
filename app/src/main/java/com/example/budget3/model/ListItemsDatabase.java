package com.example.budget3.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Bill.class, ListItem.class}, version = 1)
public abstract class ListItemsDatabase extends RoomDatabase {
    private static ListItemsDatabase instance;
    public abstract BillDao getBillDao();
    public abstract ListItemDao getListItemDao();
    public static synchronized ListItemsDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ListItemsDatabase.class, "listItemDB")
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
        private ListItemDao listItemDao;

        public InitialDataAsyncTask(ListItemsDatabase database) {
            billDao = database.getBillDao();
            listItemDao = database.getListItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bill comedyGenre = new Bill();
            comedyGenre.setBillName("Тинькофф");

            Bill romanceGenre = new Bill();
            romanceGenre.setBillName("Сбербанк");

            Bill dramaGenre = new Bill();
            dramaGenre.setBillName("Газпромбанк");

            billDao.insert(comedyGenre);
            billDao.insert(romanceGenre);
            billDao.insert(dramaGenre);


            ListItem listItem1 = new ListItem();
            listItem1.setListItemName("Bad Boys for Life");
            listItem1.setListItemDescription("The Bad Boys Mike Lowrey and Marcus Burnett are back together for one last ride in the highly anticipated Bad Boys for Life.");
            listItem1.setBillId(1);

            ListItem listItem2 = new ListItem();
            listItem2.setListItemName("Parasite");
            listItem2.setListItemDescription("All unemployed, Ki-taek and his family take peculiar interest in the wealthy and glamorous Parks, as they ingratiate themselves into their lives and get entangled in an unexpected incident.");
            listItem2.setBillId(1);

            ListItem listItem3 = new ListItem();
            listItem3.setListItemName(" Once Upon a Time... in Hollywood");
            listItem3.setListItemDescription("A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.");
            listItem3.setBillId(1);

            ListItem listItem4 = new ListItem();
            listItem4.setListItemName("You");
            listItem4.setListItemDescription("A dangerously charming, intensely obsessive young man goes to extreme measures to insert himself into the lives of those he is transfixed by.");
            listItem4.setBillId(2);

            ListItem listItem5 = new ListItem();
            listItem5.setListItemName("Little Women");
            listItem5.setListItemDescription("Jo March reflects back and forth on her life, telling the beloved story of the March sisters - four young women each determined to live life on their own terms.");
            listItem5.setBillId(2);

            ListItem listItem6 = new ListItem();
            listItem6.setListItemName("Vikings");
            listItem6.setListItemDescription("Vikings transports us to the brutal and mysterious world of Ragnar Lothbrok, a Viking warrior and farmer who yearns to explore - and raid - the distant shores across the ocean.");
            listItem6.setBillId(2);

            ListItem listItem7 = new ListItem();
            listItem7.setListItemName("1917");
            listItem7.setListItemDescription("Two young British soldiers during the First World War are given an impossible mission: deliver a message deep in enemy territory that will stop 1,600 men, and one of the soldiers' brothers, from walking straight into a deadly trap.");
            listItem7.setBillId(3);

            ListItem listItem8 = new ListItem();
            listItem8.setListItemName("The Witcher");
            listItem8.setListItemDescription("Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.");
            listItem8.setBillId(3);

            ListItem listItem9 = new ListItem();
            listItem9.setListItemName("The Outsider");
            listItem9.setListItemDescription("Investigators are confounded over an unspeakable crime that's been committed.");
            listItem9.setBillId(3);

            listItemDao.insert(listItem1);
            listItemDao.insert(listItem2);
            listItemDao.insert(listItem3);
            listItemDao.insert(listItem4);
            listItemDao.insert(listItem5);
            listItemDao.insert(listItem6);
            listItemDao.insert(listItem7);
            listItemDao.insert(listItem8);
            listItemDao.insert(listItem9);

            return null;
        }
    }

}
