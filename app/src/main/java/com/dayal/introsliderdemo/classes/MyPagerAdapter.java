package com.dayal.introsliderdemo.classes;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dayal.introsliderdemo.R;

import org.w3c.dom.Text;

public class MyPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;

    public MyPagerAdapter( Context context) {

              this.context = context;
    }

    //arrays
    public int[] slide_images = {
            R.mipmap.gp1,
            R.mipmap.gp2,
            R.mipmap.gp3
    };

    public String[] slide_headings = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slide_decrp = {
                    "    If Purple People Eaters are real… where do they find purple people to eat?" ,
                    "    Abstraction is often one floor above you",
                    "    This is the last random sentence I will be writing and I am going to stop mid-sent"

    };
    @Override
    public int getCount() {
      return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
       return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImage = (ImageView)view.findViewById(R.id.imageView);
        TextView slideHeading = (TextView)view.findViewById(R.id.heading);
        TextView slideDescp = (TextView)view.findViewById(R.id.description);

        slideImage.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideHeading.setTextColor(Color.WHITE);
        slideDescp.setText(slide_decrp[position]);
        slideDescp.setTextColor(Color.WHITE);

        container.addView(view);
        return view;
    }

    // destroys pagers when reached last
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

      //  View view = (View) object;
        container.removeView((RelativeLayout)object);
    }
}
