package com.example.mysugartracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "input1_table")
public class Input1 {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "input1")
    private String mInput1;

    Input1(@NotNull String input1) {this.mInput1 = input1;}
    public String getInput1() {return this.mInput1;}

    //getter for id
    public int getId() {
        return id;
    }
    //setter for id
    public void setId(int id) {
        this.id = id;
    }

    //allow getValue() method in Tabbed.java to retrieve input1
    public String getValue(){
        return mInput1;
    }
}
