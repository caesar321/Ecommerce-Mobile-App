package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText email,password,confirmPasssword,username,firstname,lastname;
    private Button btnregisterLogin;
    FirebaseAuth auth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.rgEdEmail);
        password = findViewById(R.id.rgEdPassword);
        lastname=  findViewById(R.id.rgLastName);
        firstname= findViewById(R.id.rFirstName);
        confirmPasssword = findViewById(R.id.ConEdPassword);
        username =  findViewById(R.id.rgEUserName);
        btnregisterLogin = findViewById(R.id.btnReg);
        progressDialog= new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
        btnregisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });
    }

    private void checkCredentials() {
        String FirstName= firstname.getText().toString();
        String LastName = lastname.getText().toString();
        String emailUser = email.getText().toString();
        String txtPassword = password.getText().toString();
        String confirmPassword = confirmPasssword.getText().toString();
        String user = username.getText().toString();
        if(FirstName.isEmpty()){
            Toast.makeText(this, "Fill in your First Name", Toast.LENGTH_SHORT).show();
        }
        else if(LastName.isEmpty()){
            Toast.makeText(this, "Enter your Last Name", Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Your Email", Toast.LENGTH_SHORT).show();
        }
        else if(user.isEmpty()){
            Toast.makeText(this, "Input user name", Toast.LENGTH_SHORT).show();
        }
        else if(txtPassword.isEmpty()){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }else if(txtPassword.length()<5){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
        }else if(confirmPassword.isEmpty()){
            Toast.makeText(this, "Confirm your password", Toast.LENGTH_SHORT).show();
        }else if(!txtPassword.equals(confirmPassword)){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.setMessage("Registering....");
            progressDialog.show();
           auth.createUserWithEmailAndPassword(email.getText().toString(),txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){
                       progressDialog.setMessage("Saving user data....");
                       Toast.makeText(RegisterActivity.this, "registration success", Toast.LENGTH_SHORT).show();
                           String uid= auth.getUid();
                           HashMap<String,Object> hashMap = new HashMap<>();
                           hashMap.put("firstName",FirstName);
                           hashMap.put("lastName",LastName);
                           hashMap.put("user",user);
                           hashMap.put("uEmail",email.getText().toString());
                           hashMap.put("password",txtPassword);
                           hashMap.put("uid",uid);
                           DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
                           assert uid != null;
                           databaseReference.child(uid).setValue(hashMap);

                       progressDialog.dismiss();
                      Intent intent = new Intent(RegisterActivity.this,MainPageActivity.class);
                      intent.putExtra("UserNameToAccount",user);
                       intent.putExtra("GmailAccount",email.getText().toString());
                       intent.putExtra("userID",uid);
                      startActivity(intent);
                              ;
                   }
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   progressDialog.dismiss();
                   Toast.makeText(RegisterActivity.this, "error", Toast.LENGTH_SHORT).show();

               }
           });

        }
    }



}