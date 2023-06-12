package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class LadiesWearActivity extends AppCompatActivity {
    private ArrayList<productModal> myProduct;
    private productAdapter productAdapter;
    private RecyclerView recyclerLW;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladies_wear);
        recyclerLW = findViewById(R.id.LWRecycler);
        myProduct = new ArrayList<>();
        goBack = findViewById(R.id.arrowLWBack);
        recyclerLW.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerLW.setHasFixedSize(true);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}