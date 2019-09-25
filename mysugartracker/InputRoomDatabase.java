package com.example.mysugartracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Input1.class}, version = 1, exportSchema = false)
public abstract class InputRoomDatabase extends RoomDatabase {
    public abstract Input1Dao input1Dao();
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    //make database a singleton to prevent it being opened in multiple places at the same time
    private static volatile InputRoomDatabase INSTANCE;

    static InputRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InputRoomDatabase.class) {
                if (INSTANCE == null) {
                    //create the database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InputRoomDatabase.class, "input1_database")
                            .addMigrations(MIGRATION_1_2)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //populate the database
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                        super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    //deletes contents of database, populates it with 2 inputs
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final Input1Dao mDao;
        PopulateDbAsync(InputRoomDatabase db) {
            mDao = db.input1Dao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Input1 input1 = new Input1("3.8");
            mDao.insert(input1);
            input1 = new Input1("5.5");
            mDao.insert(input1);
            input1 = new Input1("10.2");
            mDao.insert(input1);
            input1 = new Input1("7.6");
            mDao.insert(input1);
            return null;
        }
    }

}



