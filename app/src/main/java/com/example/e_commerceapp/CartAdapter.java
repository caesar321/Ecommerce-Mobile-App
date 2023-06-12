package com.example.e_commerceapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
  private   android.content.Context context;
   private ArrayList<productModal> myProduct;
   private android.app.AlertDialog.Builder build;


    public CartAdapter(Context context, ArrayList<productModal> myProduct) {
        this.context = context;
        this.myProduct = myProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cart_layout_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CartPName.setText(myProduct.get(position).getTitle());
        holder.CartPrice.setText("$" + String.valueOf(myProduct.get(position).getPrice()));
        Picasso.get().load(myProduct.get(position).getImage()).into(holder.CartImage);
        // holder.CartQuantity.setText(myProduct.get(position).quantity);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String sanitizedItemName = itemName.replaceAll("[.#$\\[\\]]", "");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Carts");
                String itemName= holder.CartPName.getText().toString();
                databaseReference.child(itemName)
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "item deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return myProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CartPName,CartPrice,CartQuantity;
        private ImageView CartImage,deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CartPName = itemView.findViewById(R.id.CTproductName);
            CartPrice = itemView.findViewById(R.id.CTPrice);
           // CartQuantity = itemView.findViewById(R.id.CTquantity);
            CartImage = itemView.findViewById(R.id.CTimage);
            deleteBtn= itemView.findViewById(R.id.delete);
        }
    }
}
