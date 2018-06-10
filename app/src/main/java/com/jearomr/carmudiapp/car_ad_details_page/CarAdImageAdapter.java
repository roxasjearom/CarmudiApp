package com.jearomr.carmudiapp.car_ad_details_page;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jearomr.carmudiapp.GlideApp;
import com.jearomr.carmudiapp.R;
import com.jearomr.carmudiapp.models.Image;

import java.util.List;

public class CarAdImageAdapter extends PagerAdapter {

    private List<Image> images;
    private Context context;
    private LayoutInflater layoutInflater;

    public CarAdImageAdapter(List<Image> images, Context context) {
        this.images = images;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.view_pager_item, container, false);

        ImageView ivCarImage = itemView.findViewById(R.id.ivCarImage);
        GlideApp.with(context)
                .load(images.get(position).getUrl())
                .into(ivCarImage);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
