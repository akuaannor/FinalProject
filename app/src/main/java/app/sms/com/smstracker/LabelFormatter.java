package app.sms.com.smstracker;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by jessicaannor on 27/04/2018.
 */

    public class LabelFormatter implements IAxisValueFormatter {
        private final String[] mlabels;

        public LabelFormatter(String[] mlabels) {
            this.mlabels = mlabels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mlabels[(int) value];
        }
    }
