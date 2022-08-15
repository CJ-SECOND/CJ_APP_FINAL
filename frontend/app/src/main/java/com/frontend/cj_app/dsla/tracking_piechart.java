package com.frontend.cj_app.dsla;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.model.Coury_Result;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class tracking_piechart extends Fragment {

    private PieChart pieChart;

    private int total;
    private int complete;
    private int wrong;
    private int damage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.traking_piechart, container, false);

        float per_complete = 1;
        float per_wrong = 0;
        float per_damage = 0;

        // 1. 정시 배송률(2022-05-03 일 데이터)
//        per_complete = complete.getResult().get(1).getCount() / total.getResult().get(1).getCount();

        // 2. 오배송률 (2022-05-03 일 데이터)
//        per_wrong = wrong.getResult().get(1).getCount() / 5;

        // 3. 분실파손률(2022-05-03 일 데이터)
//        per_damage = complete.getResult().get(1).getCount() / 5;

        // 차트 그리기
        pieChart = view.findViewById(R.id.piechart);
        setupPieChart();
//         loadPieChartData(per_complete, per_wrong, per_damage);
        loadPieChartData(0.97f, 0.02f, 0.01f);

        return view;
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(float a, float b, float d) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(a, "정시배송"));
        entries.add(new PieEntry(b, "오배송"));
        entries.add(new PieEntry(d, "분실/파손"));

        final int[] MY_COLORS = {Color.rgb(51,153,255), Color.rgb(255,153,51), Color.rgb(255,51,51)};

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: MY_COLORS) colors.add(c);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}