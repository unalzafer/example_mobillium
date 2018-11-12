package mobillium.mobillium.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mobillium.mobillium.R;

public class VP_SlideAdepter extends PagerAdapter {

    Activity activity;
    int[] images;
    LayoutInflater layoutInflater;

    public VP_SlideAdepter(Activity activity,int[] images) {
       this.activity=activity;
       this.images=images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.viewpager_slide_item,container,false);

        ImageView image=(ImageView)itemView.findViewById(R.id.ivProducts);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels;
        int width=displayMetrics.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);

        image.setBackgroundResource(images[position]);
        //Doldurulacak
        container.addView(itemView);
        return  itemView;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
