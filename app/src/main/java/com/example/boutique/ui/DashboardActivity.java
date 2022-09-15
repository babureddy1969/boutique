package com.example.boutique.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.boutique.R;
public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_dashboard);

        Button buttonSale = findViewById(R.id.buttonSale);
        buttonSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TransactionActivity.class));
            }
        });
        Button buttonSales = findViewById(R.id.buttonSales);
        buttonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });
        Button buttonCustomer = findViewById(R.id.buttonCustomer);
        buttonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CustomerActivity.class));
            }
        });
        Button buttonReport = findViewById(R.id.buttonReports);
        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ReportActivity.class));
            }
        });
        Button buttonItems = findViewById(R.id.buttonSarees);
        buttonItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ItemsMainActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
