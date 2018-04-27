package app.sms.com.smstracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

/**
 * Created by jessicaannor on 27/04/2018.
 */

public class Barchart extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        barChart = (BarChart) findViewById(R.id.chart1);

        float yValues [] = {10, 20};
        String xValues [] = {"Credit", "Debit"};

        YAxis yAxisL = barChart.getAxisLeft();
        YAxis yAxisR = barChart.getAxisRight();
        yAxisL.setAxisMinimum(0f);
        yAxisR.setAxisMinimum(0f);
        yAxisL.setGranularityEnabled(true);
        yAxisR.setGranularityEnabled(true);
        yAxisL.setGranularity(5f);
        yAxisR.setGranularity(5f);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);


        drawBarGraph(yValues,xValues);
    }

    private void drawBarGraph(float [] yValues, String [] xValues){
        ArrayList<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i < yValues.length; i++){
            yData.add(new BarEntry(i,yValues[i]));
        }


        BarDataSet barDataSet = new BarDataSet(yData, "Transaction");
        barDataSet.setColors(new int[]{Color.RED, Color.BLUE});


        BarData barData = new BarData(barDataSet);

        barData.setValueTextSize(13f);
        barData.setValueTextColor(Color.MAGENTA);

        barChart.getXAxis().setValueFormatter(new LabelFormatter(xValues));
        barChart.setData(barData);
        barChart.invalidate();
    }

}

