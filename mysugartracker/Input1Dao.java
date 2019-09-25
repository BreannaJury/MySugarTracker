package com.example.mysugartracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Input1Dao {
    @Query("DELETE FROM input1_table")
    void deleteAll();
    @Query("SELECT * from input1_table")
    LiveData<List<Input1>> getAllInput1s();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Input1... input1s);
}

