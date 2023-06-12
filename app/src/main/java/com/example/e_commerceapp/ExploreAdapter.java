package com.example.e_commerceapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExploreAdapter extends  RecyclerView.Adapter<ExploreAdapter.ViewHolder>{

   android.content.Context context;
    ArrayList<ExploreProductsModel> exploreProductsModels;
    boolean isClicked=true;


    public ExploreAdapter(Context context, ArrayList<ExploreProductsModel> exploreProductsModels) {
        this.context = context;
        this.exploreProductsModels = exploreProductsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.explorelayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.exName.setText(exploreProductsModels.get(position).getExProductName());
        Picasso.get().load(exploreProductsModels.get(position).getExProductImage()).into(holder.exImage);

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return exploreProductsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView exName;
        private ImageView exImage;
        private ToggleButton heart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exName = itemView.findViewById(R.id.exploreText);
            exImage = itemView.findViewById(R.id.exploreImg);
            heart = itemView.findViewById(R.id.togglebtn);
        }
    }
}
