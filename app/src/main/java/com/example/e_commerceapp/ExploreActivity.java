package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {
    private ArrayList<ExploreProductsModel> myExplore;
    private ExploreAdapter exploreAdapter;
    private RecyclerView exploreRecycler;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        exploreRecycler= findViewById(R.id.RecyclerEx);
        myExplore= new ArrayList<>();
        exploreAdapter= new ExploreAdapter(ExploreActivity.this,myExplore);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration itemDecoration= new DividerItemDecoration(getApplicationContext(),linearLayoutManager.getOrientation());
        exploreRecycler.setLayoutManager(linearLayoutManager);
        exploreRecycler.addItemDecoration(itemDecoration);
        exploreRecycler.setHasFixedSize(true);
        exploreRecycler.setAdapter(exploreAdapter);
        goBack = findViewById(R.id.arrowExploreBack);
        explore();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void explore() {
        myExplore.add(new ExploreProductsModel("Mens Casual Slim Fit","https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"));
        myExplore.add(new ExploreProductsModel("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet","https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"));
        myExplore.add(new ExploreProductsModel("Mens Casual Slim Fit","https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"));
        myExplore.add(new ExploreProductsModel("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet","https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"));
        myExplore.add(new ExploreProductsModel("DANVOUY Womens T Shirt Casual Cotton Short","https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg"));
        myExplore.add(new ExploreProductsModel("Opna Women's Short Sleeve Moisture","https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg"));
        myExplore.add(new ExploreProductsModel(" Women's Solid Short Sleeve Boat Neck V","https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg"));
        myExplore.add(new ExploreProductsModel("Rain Jacket Women Windbreaker Striped Climbing Raincoats","https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg"));
        myExplore.add(new ExploreProductsModel("Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket","https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg"));
        myExplore.add(new ExploreProductsModel("BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats","https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}