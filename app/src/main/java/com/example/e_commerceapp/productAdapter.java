package com.example.e_commerceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder>{
    private Context context;
    private ArrayList<productModal> productModals;

    public productAdapter(Context context, ArrayList<productModal> productModals) {
        this.context = context;
        this.productModals = productModals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productslayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleName.setText(productModals.get(position).getTitle());
       holder.Price.setText("$"+String.valueOf(productModals.get(position).getPrice()));
        Picasso.get().load(productModals.get(position).getImage()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,DetailsActivity.class);
                intent.putExtra("Id",productModals.get(holder.getAdapterPosition()).getId());
                intent.putExtra("Name",productModals.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Image",productModals.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Price",productModals.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("Category",productModals.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("Rating",productModals.get(holder.getAdapterPosition()).getRating());
                intent.putExtra("Description",productModals.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Brand",productModals.get(holder.getAdapterPosition()).getBrand());
                intent.putExtra("Material",productModals.get(holder.getAdapterPosition()).getMaterial());
                intent.putExtra("Weight",productModals.get(holder.getAdapterPosition()).getWeight());
                intent.putExtra("Condition",productModals.get(holder.getAdapterPosition()).getCondition());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleName,Price;
        private ImageView image;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleName = itemView.findViewById(R.id.productname);
            Price = itemView.findViewById(R.id.productprice);
            image= itemView.findViewById(R.id.imageViewProduct);
            cardView = itemView.findViewById(R.id.cardViewForClick);
        }
    }
}
