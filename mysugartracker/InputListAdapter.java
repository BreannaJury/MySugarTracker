package com.example.mysugartracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InputListAdapter extends RecyclerView.Adapter<InputListAdapter.InputViewHolder> {

    class InputViewHolder extends RecyclerView.ViewHolder {
        private final TextView input1ItemView;

        private InputViewHolder(View itemView) {
            super(itemView);
            input1ItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Input1> mInput1s; // Cached copy of words

    InputListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public InputViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.inputlist_item, parent, false);
        return new InputViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InputViewHolder holder, int position) {
        if (mInput1s != null) {
            Input1 current = mInput1s.get(position);
            holder.input1ItemView.setText(current.getInput1());
        } else {
            // Covers the case of data not being ready yet.
            holder.input1ItemView.setText("No Input");
        }
    }

    void setInput1s(List<Input1> input1s){
        mInput1s = input1s;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mInputs has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mInput1s != null)
            return mInput1s.size();
        else return 0;
    }

}
