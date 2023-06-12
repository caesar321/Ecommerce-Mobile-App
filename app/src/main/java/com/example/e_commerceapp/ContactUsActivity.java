package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {
    private EditText GmailAddressEditText;
    private EditText SubjectAddressEditText;
    private EditText MessageEditText;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
GmailAddressEditText = findViewById(R.id.gmailAddress);
SubjectAddressEditText= findViewById(R.id.SubjectAddress);
MessageEditText = findViewById(R.id.Message);
goBack = findViewById(R.id.arrowContactBack);
goBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});



        Button btnSend = findViewById(R.id.btnSendMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String email= GmailAddressEditText.getText().toString();
       String emailTo =("henrycharles360@gmail.com");
        String[] recipient = email.split(",");
        String subject= SubjectAddressEditText.getText().toString();
        String message = MessageEditText.getText().toString();
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));

    }
}