package com.dayal.introsliderdemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayal.introsliderdemo.R;
import com.dayal.introsliderdemo.classes.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private MyPagerAdapter slideAdapter;
    private LinearLayout dotsLayout;

    private Button prevBtn;
    private Button nextBtn;

    private int currPage;

    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideViewPager = (ViewPager)findViewById(R.id.viewPager);
        dotsLayout = (LinearLayout)findViewById(R.id.dots_layoutID);
        prevBtn = (Button)findViewById(R.id.prevBtn);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        slideAdapter = new MyPagerAdapter(this);

        dots = new TextView[3];

        slideViewPager.setAdapter(slideAdapter);

        slideViewPager.addOnPageChangeListener(viewListener);

       onClickEvent(0);

      addDotsIndicator(0);

    }

    public void addDotsIndicator(int position){
        dotsLayout.removeAllViews();

       dotsLayout.setGravity(Gravity.CENTER);

        for(int i=0; i<dots.length; i++){

            dots[i] = new TextView(getApplicationContext());
            dots[i].setText(fromHtml("&#8226;"));     // (Html.fromHtml())  -- depricated
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.inactiveDots)); //deprecated-- getResourses().getColor()

            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(ContextCompat.getColor(this,R.color.activeDots));


    }
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    public void onClickEvent(int pos){
        if(pos == 0) {
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideViewPager.setCurrentItem(currPage + 1);
                }
            });

            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    finish();
                }
            });
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            currPage = position;

            if(currPage == 0){
                prevBtn.setText("SKIP");
                nextBtn.setText("NEXT");

               onClickEvent(currPage);

            }else if(currPage == dots.length-1){

                prevBtn.setText("PREVIOUS");
                nextBtn.setText("FINISH");

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currPage+1);
                    }
                });
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
                        finish();
                    }
                });
            }else{
                prevBtn.setText("PREVIOUS");
                nextBtn.setText("NEXT");

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currPage+1);
                    }
                });
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currPage-1);
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
