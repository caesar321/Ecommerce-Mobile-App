package com.example.e_commerceapp;

import static com.paypal.android.sdk.cy.i;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AddressActivity extends AppCompatActivity {
    private EditText fname,lname,address,phonenumber;
    private Button btnsave;
    private ImageView goBack;
   ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        fname = findViewById(R.id.fnameAddress);
        lname = findViewById(R.id.lnameAddress);
        address = findViewById(R.id.adressrecepient);
        phonenumber = findViewById(R.id.phoneNumber);
        progressDialog = new ProgressDialog(this);
        goBack= findViewById(R.id.arrowAddressBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnsave = findViewById(R.id.btnSaveAddress);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.getText().toString().isEmpty()){
                    Toast.makeText(AddressActivity.this, "Please Enter your first Name for some purpose", Toast.LENGTH_SHORT).show();
                }
                else if(lname.getText().toString().isEmpty()){
                    Toast.makeText(AddressActivity.this, "Please Enter your last name for some purpose", Toast.LENGTH_SHORT).show();
                }
                else if(phonenumber.getText().toString().isEmpty()){
                    Toast.makeText(AddressActivity.this, "Please Enter your phone number for some purpose", Toast.LENGTH_SHORT).show();
                }
                else if(address.getText().toString().isEmpty()){
                    Toast.makeText(AddressActivity.this, "Please Enter your Address for some purpose", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Intent intent = new Intent(AddressActivity.this, OrderActivity.class);
                            Toast.makeText(AddressActivity.this, "Address saved ", Toast.LENGTH_SHORT).show();
                            intent.putExtra("Address", address.getText().toString());
                            startActivity(intent);
                        }
                    }, 10000);
                }
            }
        });

    }
}