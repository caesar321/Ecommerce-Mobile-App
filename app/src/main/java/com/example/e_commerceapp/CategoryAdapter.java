package com.example.e_commerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<myCategory> myCategory;

    public CategoryAdapter(Context context, ArrayList<myCategory> myCategory) {
        this.context = context;
        this.myCategory = myCategory;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoryadapteritem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.Categoryname.setText(myCategory.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return myCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView Categoryname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Categoryname= itemView.findViewById(R.id.txtName);
        }
    }
}
