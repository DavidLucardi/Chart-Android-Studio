package com.example.davidyl.chart_davidyl;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import org.achartengine.ChartFactory;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class MainActivity extends AppCompatActivity {
    private View mChart;
    private String[] mMonth = new String[]{"lan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    Button but;

    @Override
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);

        but = (Button) findViewById(R.id.buttonl);
        View.OnClickListener klik = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openChart();
            }
        };
        but.setOnClickListener(klik);
    }

    protected void openChart() {
        int[] x = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] income = {2000, 2500, 2700, 3000, 2800, 3500, 3700, 3800, 0, 0, 0, 0};
        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0,
                0, 0};
        XYSeries incomeSeries = new XYSeries("Income");
        XYSeries expenseSeries = new XYSeries("Expense");

        for (int i = 0; i < x.length; i++) {
            incomeSeries.add(i, income[i]);
            expenseSeries.add(i, expense[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(incomeSeries);
        dataset.addSeries(expenseSeries);

        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.CYAN);
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.GREEN);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);

        for (int i = 0; i < x.length; i++) {
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);
        LinearLayout chartLayout = (LinearLayout) findViewById(R.id.chart);
        chartLayout.removeAllViews();
        mChart = ChartFactory.getLineChartView(MainActivity.this, dataset, multiRenderer);
        chartLayout.addView(mChart);
    }
}