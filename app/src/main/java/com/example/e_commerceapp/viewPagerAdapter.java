package com.example.e_commerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class viewPagerAdapter extends PagerAdapter {
    Context context;
    int images[] = {
            R.drawable.cartimage,
            R.drawable.people,
            R.drawable.clothes
    };
    int des[]={
            R.string.string_title1,
            R.string.string_title2 ,
            R.string.string_title3,

    };

    public viewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.item_layout,container,false);
        ImageView sliderimage= (ImageView)view.findViewById(R.id.image1);
        TextView txtslidertext = (TextView) view.findViewById(R.id.title_1);
        sliderimage.setImageResource(images[position]);
        txtslidertext.setText(des[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((LinearLayout)object);

    }
}
