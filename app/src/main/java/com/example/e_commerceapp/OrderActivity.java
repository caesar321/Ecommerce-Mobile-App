package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class OrderActivity extends AppCompatActivity {
    private TextView totalItem,sumOfItems,DeliveryFee,TotalFee,Address,AddressInfoofBuyer;
    private Button btnLocation,btnNext,btnPrevious;
    private ImageView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        totalItem= findViewById(R.id.totalItems);
        sumOfItems = findViewById(R.id.orderItemTotal);
        DeliveryFee = findViewById(R.id.orderDeliveryFee);
        TotalFee = findViewById(R.id.orderTotalFee);
        Address = findViewById(R.id.newAddress);
        btnLocation = findViewById(R.id.btnSelectLocation);
        AddressInfoofBuyer = findViewById(R.id.locatebuyer);
        goBack = findViewById(R.id.arrowOrderBack);

        btnNext= findViewById(R.id.next);
        btnPrevious = findViewById(R.id.previous);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });
        Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
         int totalItems = bundle.getInt("TotalItems");
        totalItem.setText(String.valueOf(totalItems));
        String selectedAddress = bundle.getString("Address");
        if(selectedAddress!=null){
            AddressInfoofBuyer.setText("No Delivery Address availabe");
        }
        else {
            AddressInfoofBuyer.setText(selectedAddress);
        }
        //AddressInfoofBuyer.setText(selectedAddress);
       // double TTI = totalItems;


       String DFdemo =  bundle.getString("TotalPrice");
       sumOfItems.setText(bundle.getString("TotalPrice"));
       double DFdemo1 = Double.parseDouble(DFdemo);
       double deliverFee = DFdemo1*0.4;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double roundedDeliveryFee= Double.parseDouble(decimalFormat.format(deliverFee));
       DeliveryFee.setText(String.valueOf(roundedDeliveryFee) );
       double Totalprice = DFdemo1 + deliverFee;
       double roundedTotalPrice = Double.parseDouble(decimalFormat.format(Totalprice));
       TotalFee.setText(String.valueOf(roundedTotalPrice));

       btnPrevious.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(OrderActivity.this,CartActivity.class);
               startActivity(intent);
           }
       });
        btnNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(OrderActivity.this,Payment1Activity.class);
        startActivity(intent);
    }
});

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}