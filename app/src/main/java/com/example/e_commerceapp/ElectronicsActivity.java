package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ElectronicsActivity extends AppCompatActivity {
    private ArrayList<productModal> myProduct;
    private productAdapter productAdapter;
    private RecyclerView recyclerElects;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        recyclerElects= findViewById(R.id.cartRecyclerElect);
        myProduct= new ArrayList<>();
        LinearLayoutManager linearLayoutManager;
        goBack = findViewById(R.id.arrowElectronicBack);

        recyclerElects.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerElects.setHasFixedSize(true);
        myProduct.add(new productModal(10,129,2.6,"SanDisk SSD PLUS 1TB Internal SSD - SATA III ","https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg","Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.","electronics","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(11,109,4.8,"Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III","https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg","3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.","electronics","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(12,114,4.8,"WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive","https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg","Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty.","Electronic","NA","fufu","New","40","4.0"));
        myProduct.add(new productModal(13,599,2.9,"Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin","https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg","21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz.","electronics","NA","fufu","New","40","4.0"));
   productAdapter = new productAdapter(getApplicationContext(),myProduct);
   recyclerElects.setAdapter(productAdapter);
    }

}