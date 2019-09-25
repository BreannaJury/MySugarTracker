package com.example.mysugartracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InputRepository {
    private Input1Dao mInput1Dao;
    private LiveData<List<Input1>> mAllInput1s;

    InputRepository(Application application) {
        InputRoomDatabase db = InputRoomDatabase.getDatabase(application);
        mInput1Dao = db.input1Dao();
        mAllInput1s = mInput1Dao.getAllInput1s();
    }
    LiveData<List<Input1>> getAllInput1s() {
        return mAllInput1s;
    }

    public void insert (Input1 input1) {
        new insertAsyncTask(mInput1Dao).execute(input1);
    }
    private static class insertAsyncTask extends AsyncTask<Input1, Void, Void> {
        private Input1Dao mAsyncTaskDao;
        insertAsyncTask(Input1Dao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Input1...params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
