package com.example.mysugartracker;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InputViewModel extends AndroidViewModel {
    private InputRepository mRepository;
    private LiveData<List<Input1>> mAllInput1s;
    public InputViewModel (Application application) {
        super(application);
        mRepository = new InputRepository(application);
        mAllInput1s = mRepository.getAllInput1s();
    }
    LiveData<List<Input1>> getAllInput1s() {return mAllInput1s;}
    void insert(Input1 input1) {mRepository.insert(input1);}
}
