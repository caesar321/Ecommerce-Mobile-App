package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


public class DetailsActivity extends AppCompatActivity {
    private ImageView detailImage, goBack;
    private TextView desc, NameOfProduct, rating, price, Category, review, AboutItems, Review, brandType, Condition, material, weight, description, category2;
    private Button btnAddToCart, BuyNow;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailImage = findViewById(R.id.detailImgView);
        Category = findViewById(R.id.txtCategory);
        description = findViewById(R.id.description);
        NameOfProduct = findViewById(R.id.txtDetailProducttitle);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        review = findViewById(R.id.DetailPreview);
        AboutItems = findViewById(R.id.DetailAbout);
       // Review = findViewById(R.id.DetailReview);
        brandType = findViewById(R.id.detailBrandType);
        category2 = findViewById(R.id.detailCatType);
        btnAddToCart = findViewById(R.id.detailBtnCart);
        Condition = findViewById(R.id.detailCOndition);
        storageReference = FirebaseStorage.getInstance().getReference();
        material = findViewById(R.id.DetailMaterial);
        builder= new AlertDialog.Builder(DetailsActivity.this);
        weight = findViewById(R.id.DetailWeight);
        goBack = findViewById(R.id.arrowDetailBack);

        price = findViewById(R.id.detailPrice);
        rating = findViewById(R.id.detailProductRating);
        //  btnAddToCartDetail= findViewById(R.id.btnProductcart);

        Bundle bundle = getIntent().getExtras();

            String name = bundle.getString("Name");
            NameOfProduct.setText(name);
           
        Category.setText(bundle.getString("Category"));
        category2.setText(bundle.getString("Category"));
        description.setText(bundle.getString("Description"));
        brandType.setText(bundle.getString("Brand"));
        material.setText(bundle.getString("Material"));
        weight.setText(bundle.getString("Weight"));
        Condition.setText(bundle.getString("Condition"));
        price.setText("$" + String.valueOf(bundle.getDouble("Price")));
        Picasso.get().load(bundle.getString("Image")).into(detailImage);
        rating.setText(String.valueOf(bundle.getDouble("Rating")));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



       btnAddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Adding to Cart...");
                progressDialog.show();
                String imageFileName = bundle.getString("Name") + "_" + System.currentTimeMillis() + ".jpg";
                String imageUrl = getIntent().getStringExtra("Image");
                Picasso.get().load(imageUrl).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        // Convert the Bitmap to a byte array
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageData = baos.toByteArray();

                        StorageReference imageRef = storageReference.child("images/" + imageFileName);
                        UploadTask uploadTask = imageRef.putBytes(imageData);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get the download URL for the uploaded image
                                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        // Save the movie data and image URL to Firebase Realtime Database
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("title", bundle.getString("Name"));
                                      //  hashMap.put("$user_ID", bundle.getString("userID"));
                                        hashMap.put("price", bundle.getDouble("Price"));
                                        hashMap.put("image", uri.toString());
                                        String priceString = String.valueOf(bundle.getDouble("Price"));
                                        String childPath = priceString.replace(".", "");
                                       // databaseReference.child(childPath).setValue(hashMap);

                                        databaseReference = FirebaseDatabase.getInstance().getReference("Carts");
                                        databaseReference.child(childPath).setValue(hashMap);

                                          progressDialog.dismiss();
                                        Toast.makeText(DetailsActivity.this, "Saving data successfully", Toast.LENGTH_SHORT).show();
                                       builder.setTitle("Cart")
                                               .setMessage("Added to cart")
                                               .setCancelable(true)
                                               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialogInterface, int i) {

                                                   }
                                               }).show();
                                         /*AlertDialog.Builder builder= new AlertDialog.Builder(getApplicationContext());
                                        builder.setTitle("Added To CART")
                                                .setMessage("Product added to cart")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }).show();*/

                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // progressDialog.dismiss();
                                Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        //  progressDialog.dismiss();
                        Toast.makeText(DetailsActivity.this, "An error occured while trying to load the image..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });
    }
}

