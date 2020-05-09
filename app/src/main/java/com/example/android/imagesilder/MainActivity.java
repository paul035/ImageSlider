package com.example.android.imagesilder;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    private Timer timer;
    private int current_position = 0;
    private LinearLayout dotsLayout;
    private int custom_position = 0;

    private int[] image_resource = {R.drawable.img_0, R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotsLayout = findViewById(R.id.dotsContainer);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        customSwipeAdapter = new CustomSwipeAdapter(this, image_resource);
        viewPager.setAdapter(customSwipeAdapter);

        //After the view pager is set we call the createSlideShow method for automatic swiping
        createSlideShow();
    }

    //This method will automatically slide the images
    private void createSlideShow(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(current_position == Integer.MAX_VALUE){
                    current_position = 0;
                }
                viewPager.setCurrentItem(current_position++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },250, 1500);
    }
}
