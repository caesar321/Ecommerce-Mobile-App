package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity {
    private TextView userName1,gmailUser;
    private Button btnOrderSummary,btnContactUs,accountManagement,deleteAccount,LogOut;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    private AlertDialog.Builder builder;

  //  FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        userName1=  findViewById(R.id.acctUserName);
        gmailUser = findViewById(R.id.actGmail);
        btnOrderSummary= findViewById(R.id.acctOrderSum);
        btnOrderSummary.setEnabled(false);
        accountManagement= findViewById(R.id.acctManagement);
        accountManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AccountActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        btnContactUs= findViewById(R.id.contactUs);
        builder= new AlertDialog.Builder(AccountActivity.this);
        LogOut = findViewById(R.id.LogOut);
       LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("LogOut")
                                .setMessage("Are you sure you want to Log out?")
                                        .setCancelable(true)
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        auth.signOut();
                                                        Intent intent= new Intent(AccountActivity.this,LoginActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });


        btnOrderSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });


        //Bundle bundle = getIntent().getExtras();

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        DatabaseReference userRef= databaseReference.child(Objects.requireNonNull(auth.getUid()));
        DatabaseReference userName= userRef.child("user");
        userName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                userName1.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        DatabaseReference userRef2= databaseReference.child(Objects.requireNonNull(auth.getUid()));
        DatabaseReference userGmail= userRef2.child("uEmail");
        userGmail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.getValue(String.class);
                gmailUser.setText(name1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       btnContactUs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(AccountActivity.this,ContactUsActivity.class);
               startActivity(intent);
           }
       });
    }
}