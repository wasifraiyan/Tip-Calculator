package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etBaseAmount;
    private TextView tvTipPercentage, tvTipAmount, tvTotalAmount, tvTipMessage;
    private SeekBar seekBarTipPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBaseAmount = findViewById(R.id.et_base_amount);
        tvTipPercentage = findViewById(R.id.tv_tip_percentage);
        tvTipAmount = findViewById(R.id.tv_tip_amount);
        tvTotalAmount = findViewById(R.id.tv_total_amount);
        tvTipMessage = findViewById(R.id.tv_tip_message);
        seekBarTipPercentage = findViewById(R.id.seekbar_tip_percentage);


        seekBarTipPercentage.setProgress(15);


        seekBarTipPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTipPercentage(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void updateTipPercentage(int tipPercentage) {
        String baseAmountStr = etBaseAmount.getText().toString();
        if (baseAmountStr.isEmpty()) {
            baseAmountStr = "0";
        }
        double baseAmount = Double.parseDouble(baseAmountStr);

        double tipAmount = (baseAmount * tipPercentage) / 100;
        double totalAmount = baseAmount + tipAmount;

        DecimalFormat df = new DecimalFormat("#.##");


        tvTipPercentage.setText(tipPercentage + "%");
        tvTipAmount.setText("Tip  " + df.format(tipAmount));
        tvTotalAmount.setText("Total  " + df.format(totalAmount));


        if (tipPercentage < 10) {
            tvTipMessage.setText("Poor");
            tvTipMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (tipPercentage <= 15) {
            tvTipMessage.setText("Acceptable");
            tvTipMessage.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else if (tipPercentage <= 20) {
            tvTipMessage.setText("Good");
            tvTipMessage.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            tvTipMessage.setText("Amazing");
            tvTipMessage.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}
