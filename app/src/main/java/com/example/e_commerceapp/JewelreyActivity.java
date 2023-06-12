package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class JewelreyActivity extends AppCompatActivity {
    private ArrayList<productModal> myProduct;
    private productAdapter productAdapter;
    private RecyclerView recyclerJew;
    private ImageView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewelrey);
        recyclerJew = findViewById(R.id.JewRecyclerJew);
        myProduct = new ArrayList<>();
        goBack= findViewById(R.id.arrowJewlreyBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerJew.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerJew.setHasFixedSize(true);
         //myProduct.add(new productModal(, 100.00, "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",3.9,"Men Wear","palm Angelas","cotton","New","100","200"));
         myProduct.add(new productModal(5,695, 4.6,"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg","From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(5, 168, 3.9,"Solid Gold Petite Micropave", "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg","Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(5,9.99, 4.6,"White Gold Plated Princess", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg","estowed with love and abFrom our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bundance, or outward for protection.","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(5,695, 3.0,"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet", "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg","Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine's Day...","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(5,10.99, 1.9,"Pierced Owl Rose Gold Plated Stainless Steel Double", "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg","Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        productAdapter= new productAdapter(JewelreyActivity.this,myProduct);
        recyclerJew.setAdapter(productAdapter);
    }
}