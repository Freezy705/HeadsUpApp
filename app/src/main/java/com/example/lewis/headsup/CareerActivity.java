package com.example.lewis.headsup;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lewis.headsup.data.User;
import com.example.lewis.headsup.data.UserHandler;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class CareerActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);

        user = UserHandler.getUser(this);
        constructChart();

    }

    private void constructChart(){
        int[] states = user.getStates();
        List<PieEntry> entries = new ArrayList<>();
        PieChart pieChart = findViewById(R.id.pc_progress);

        entries.add(new PieEntry(states[0],"Finished Tasks"));
        entries.add(new PieEntry(states[1],"Untouched Tasks"));
        entries.add(new PieEntry(states[2],"Incomplete Tasks"));

        PieDataSet pieDataSet = new PieDataSet(entries,"Your Task Progress");
        int[] colours = {Color.GREEN,Color.YELLOW,Color.GRAY};
        pieDataSet.setColors(colours);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
