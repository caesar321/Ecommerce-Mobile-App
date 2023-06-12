package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private ArrayList<productModal> productModals;
private DatabaseReference reference;
private CartAdapter cartAdapter;
private ImageView cartImageToBack;
private Button btnContinue;
private TextView STTotalBalance;
private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartRecyclerView= findViewById(R.id.cartRecycler);
        productModals= new ArrayList<>();
        cartImageToBack= findViewById(R.id.arrowCartBack);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        STTotalBalance= findViewById(R.id.totalBalance);
        btnContinue = findViewById(R.id.btncontinue);
        cartAdapter= new CartAdapter(getApplicationContext(),productModals);
        //cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration itemDecoration= new DividerItemDecoration(getApplicationContext(),linearLayoutManager.getOrientation());
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        cartRecyclerView.addItemDecoration(itemDecoration);
        cartRecyclerView.setHasFixedSize(true);
cartImageToBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});
        reference= FirebaseDatabase.getInstance().getReference("Carts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.show();
                productModals.clear();
                for(DataSnapshot shot: snapshot.getChildren()){
                    productModal modal = shot.getValue(productModal.class);
                    productModals.add(modal);
                }
                cartAdapter.notifyDataSetChanged();
                cartRecyclerView.setAdapter(cartAdapter);

                progressDialog.dismiss();
                double totalPrice= 0.0;
                for(productModal pro: productModals){
                    double price= pro.getPrice();
                    totalPrice+= price;
                }
                String formattedPrice = String.format("%.2f",totalPrice);
                STTotalBalance.setText("$"+formattedPrice);
                int TotalProducts = productModals.size();
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(CartActivity.this,OrderActivity.class);
                        intent.putExtra("TotalItems",TotalProducts);
                        intent.putExtra("TotalPrice",formattedPrice);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartActivity.this, "Something occured"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}