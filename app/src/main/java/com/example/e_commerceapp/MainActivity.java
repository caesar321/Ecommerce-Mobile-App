package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    private TextView skip;
    FloatingActionButton fab;
    LinearLayout layout;
    TextView[]dots;
    viewPagerAdapter adapter;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        skip = findViewById(R.id.txtSkip);
        auth= FirebaseAuth.getInstance();
       fab = findViewById(R.id.fab);
       layout = findViewById(R.id.layout_indicator);
       adapter = new viewPagerAdapter(this);
       if(auth.getCurrentUser()!= null){
           Intent intent= new Intent(MainActivity.this,MainPageActivity.class);
           startActivity(intent);
           finish();
       }
       viewPager.setAdapter(adapter);
       setUp(0);
       viewPager.addOnPageChangeListener(view_pager);

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(getItem(0)<2){
                  viewPager.setCurrentItem(getItem(1),true);
               }else{
                  Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                  startActivity(intent);
                  finish();

               }
           }
       });
       skip.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
           }
       });

    }
    public void setUp(int position){
        dots = new TextView[3];
        layout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&w8226"));
            dots[i].setTextSize(35);
            layout.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.lightGreen));
    }
     ViewPager.OnPageChangeListener view_pager = new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
                setUp(position);

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     };
    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }
}
