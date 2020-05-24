package com.example.android.imagesilder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ankur Pal on 07/05/2020
 */
public class CustomSwipeAdapter extends PagerAdapter{

    private int[] image_resource = {R.drawable.img_0, R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,};
    private Context context;
    private LayoutInflater layoutInflater;

    //Constructor for CustomSwipeAdapter class
    public CustomSwipeAdapter(Context context){
        this.context = context;
    }

    //This method will return the number of images
    //which is equal to the length of image_resource array
    @Override
    public int getCount() {
        //return image_resource.length;
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    //This method will return object that will represent
    //each of swipe view
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Initialize layoutInflater object
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Create a view and inflate it with swipe layout
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_resource[position]);
        container.addView(item_view);
        return item_view;
    }

    //This method will clear heap memory if move from one to another slider
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove object form container
        container.removeView((LinearLayout)object);
    }
}
