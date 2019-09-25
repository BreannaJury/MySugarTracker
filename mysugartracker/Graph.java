package com.example.mysugartracker;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Graph extends Fragment {

    public Graph() {
        // Required empty public constructor
    }

    private LineChart mChart;
    private InputViewModel mInputViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);


        Log.e("create","graph oncreate method reached");

        //initialize chart
        mChart = view.findViewById(R.id.chart);
        //set description
        Description description = new Description();
        description.setText("Blood glucose levels");
        mChart.setDescription(description);
        //set description if no data is available
        mChart.setNoDataText("No Data Available. Add a blood glucose level input to see your graph");
        //enable touch gestures
        mChart.setTouchEnabled(true);
        //enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        //draw background grid
        mChart.setDrawGridBackground(true);
        //draw x-axis
        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);
        // setting position to TOP and INSIDE the chart
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        //  setting text size for our axis label
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);

        // to draw axis line
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = mChart.getAxisLeft();
        // setting the count of Y-axis label's
        yAxis.setLabelCount(12, false);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setDrawGridLines(true);

        //add upper limit line
        LimitLine upper = new LimitLine(8, "Above target range");
        upper.setLineColor(Color.parseColor("#bb2d2d"));
        upper.setLineWidth(3f);
        upper.enableDashedLine(10f, 10f, 0f);
        upper.setTextColor(Color.BLACK);
        upper.setTextSize(9f);
        yAxis.addLimitLine(upper);
        //add lower limit line
        LimitLine lower = new LimitLine(4, "Below target range");
        lower.setLineColor(Color.parseColor("#2c8ec7"));
        lower.setLineWidth(3f);
        lower.enableDashedLine(10f, 10f, 0f);
        lower.setTextColor(Color.BLACK);
        lower.setTextSize(9f);
        yAxis.addLimitLine(lower);

        yAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(false);



        // add data
        setData();

        mChart.notifyDataSetChanged();
        mChart.invalidate();



        return view;
    }

    private void setData() {

        final ArrayList<Entry> values = new ArrayList<>();

        values.add(new Entry(0, (float) 3.8));
        values.add(new Entry(1,(float) 5.5));
        values.add(new Entry(2, (float) 10.2));
        values.add(new Entry(3,(float) 7.6));

        Log.e("set", "method reached");

        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "Blood Glucose Levels");
            set1.setDrawIcons(false);
            set1.setLineWidth(2f);
            set1.setCircleRadius(5f);
            //int[] colors  = new int[] {R.color.colorButton  ,R.color.colorBold, R.color.colorHeading ,R.color.colorBold, };
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(10f);
            set1.setFormLineWidth(1f);
            set1.setFormSize(15.f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            /*
            if (d > 8.0) {
                set1.setColors(Color.parseColor("#bb2d2d"));
                set1.setCircleColor(Color.parseColor("#bb2d2d"));
            }
            if (d < 4.0) {
                set1.setColors(Color.parseColor("#2c8ec7"));
                set1.setCircleColor(Color.parseColor("#2c8ec7"));
            }
            else {
                set1.setColors(Color.parseColor("#7cc576"));
                set1.setCircleColor(Color.parseColor("#7cc576"));
            } */
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        final LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.notifyDataSetChanged();
        mChart.invalidate();

        //implement viewModel and liveData
        mInputViewModel = ViewModelProviders.of(this).get(InputViewModel.class);
        mInputViewModel.getAllInput1s().observe(this, new Observer<List<Input1>>() {
            @Override
            public void onChanged(@Nullable final List<Input1> input1s) {
                Log.e("ViewModel","reaching onChanged");

                /*if (input1s != null){
                    Log.e("not null","reached input1s if");
                    String iData = String.valueOf(input1s);
                    float count = 0;
                    float mcount = count + 1;
                    values.add(new Entry(2, 4));
                    //float myDataSet = Float.parseFloat("iData");
                    //values.add(new Entry(mcount, myDataSet));
                }

                 */
                mChart.notifyDataSetChanged();
                mChart.invalidate();
            }
        });

        mChart.notifyDataSetChanged();
        mChart.invalidate();

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("resume", "onResume reached");
        setData();
    }


}


