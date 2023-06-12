package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MenWearActivity extends AppCompatActivity {
    private ArrayList<productModal> myProduct;
    private productAdapter productAdapter;
    private RecyclerView recyclerMW;
    private ImageView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_wear);
        recyclerMW = findViewById(R.id.MWRecycler);
        myProduct = new ArrayList<>();
        goBack = findViewById(R.id.arrowMWBack);
        recyclerMW.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerMW.setHasFixedSize(true);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        myProduct.add(new productModal(1,100.0,3.6,"Mens Casual Premium Slim Fit T-Shirts","https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg","Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.","Jewellrey","Gucci","Cotton","New","100","2.8"));
        // myProduct.add(new productModal(1, 100.00, "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",3.9,"Men Wear","palm Angelas","cotton","New","100","200"));
        // myProduct.add(new productModal(2, 200.90, 4.3,"Mens Casual Premium Slim Fit T-Shirts", "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg","Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.","Jewelrey","HenryBrand","Cotton","New","75","3.5"));
        myProduct.add(new productModal(2, 230.97, 3, "Mens Cotton Jacket","https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg","great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.","Men wear","Cotton","Leather","New","100","4.9"));
        myProduct.add(new productModal(3, 695 ,4.3,"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg","From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection","Clothes","Addidas","Leather","New","70","2.4"));
        myProduct.add(new productModal(4, 40.55,4.4, "Mens Casual Slim Fit", "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg","The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.","Men Collection","Gucci","Leather","New","100","2.7"));
    productAdapter= new productAdapter(MenWearActivity.this,myProduct);
    recyclerMW.setAdapter(productAdapter);
    }

}