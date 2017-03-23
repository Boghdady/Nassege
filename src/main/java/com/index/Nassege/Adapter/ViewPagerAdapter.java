package com.index.Nassege.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.index.Nassege.R;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by boghdady on 21/03/17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater layoutInflater;
    private Integer [] imgegs = {R.drawable.slider4,R.drawable.slid2,R.drawable.slid3};

    public ViewPagerAdapter(Context context, Integer[] imgegs) {
        this.context = context;
        this.imgegs = imgegs;
    }
    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgegs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.image_view_pager);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setScaleType(CENTER_CROP);
        Glide.with(context).load(imgegs[position]).into(img);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

