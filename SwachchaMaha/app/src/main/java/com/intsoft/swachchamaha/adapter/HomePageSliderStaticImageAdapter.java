package com.intsoft.swachchamaha.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intsoft.swachchamaha.R;

/**
 * Created by prafulljoshi on 12/09/17.
 */

public class HomePageSliderStaticImageAdapter extends PagerAdapter {

    Context context;

    private int[] sliderImages;

    private int[] sliderImagesTexts = new int[] {
            R.string.app_name, R.string.copyright_on_splash, R.string.app_name, R.string.copyright_on_splash, R.string.app_name
    };

    public HomePageSliderStaticImageAdapter(Context context, int[] sliderImages) {
        this.context = context;
        this.sliderImages = sliderImages;
    }
    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return sliderImages.length;
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by . This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflator = LayoutInflater.from(context);
        View inflatedView = inflator.inflate(R.layout.home_page_slider, container, false);
        ImageView sliderImageView = (ImageView) inflatedView.findViewById(R.id.sliderImageView);
        sliderImageView.setImageResource(sliderImages[position]);

        TextView sliderImageTaxtView = (TextView) inflatedView.findViewById(R.id.sliderImageTextView);
        sliderImageTaxtView.setText(sliderImagesTexts[position]);
        ((ViewPager)container).addView(inflatedView);

        return inflatedView;


       /* LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(context);
        //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setImageResource(sliderImages[position]);
        linearLayout.addView(imageView, 0);
        // ((ViewPager)container).addView(imageView, 0);

        TextView textView = new TextView(context);
        textView.setText(sliderImagesTexts[position]);

        linearLayout.addView(textView, 1);

        ((ViewPager)container).addView(linearLayout, 0);
        return linearLayout;*/
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param object    The same object that was returned by
     *                  {@link #instantiateItem(View, int)}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
