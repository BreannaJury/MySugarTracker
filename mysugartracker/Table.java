package com.example.mysugartracker;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Table extends Fragment {

    public Table() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private InputListAdapter mAdapter;
    private InputViewModel mInputViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("table","table oncreate method reached");
        //add recycler view
        final View rootView = inflater.inflate(R.layout.fragment_table, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        //set adapter
        final InputListAdapter adapter1 = new InputListAdapter(getContext());
        recyclerView.setAdapter(adapter1);
        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //show input list in recycler view
        mInputViewModel = ViewModelProviders.of(this).get(InputViewModel.class);
        mInputViewModel.getAllInput1s().observe(this, new Observer<List<Input1>>() {
            @Override
            public void onChanged(@Nullable final List<Input1> input1s) {
                // Update the cached copy of the words in the adapter.
                adapter1.setInput1s(input1s);
            }
        });
        return rootView;
    }

}
