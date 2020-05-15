package com.example.android.imagesilder;

import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
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
        if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);

        dotsLayout = findViewById(R.id.dotsContainer);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        customSwipeAdapter = new CustomSwipeAdapter(this, image_resource);
        viewPager.setAdapter(customSwipeAdapter);
        //Prepare dots layout
        prepareDots(custom_position++);
        //After the view pager is set we call the createSlideShow method for automatic swiping
        createSlideShow();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(custom_position > 4){
                    custom_position = 0;
                }
                prepareDots(custom_position++);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        },250, 2500);
    }

    private void prepareDots(int currentSlidePosition){
        //If image view is already present then remove all views
        if(dotsLayout.getChildCount() > 0){
            dotsLayout.removeAllViews();
        }
        ImageView dots[] = new ImageView[5];
        for(int i = 0; i < 5; i++){
            dots[i] = new ImageView(this);
            if(i == currentSlidePosition){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 0, 4, 0);
            dotsLayout.addView(dots[i], layoutParams);
        }
    }
}
