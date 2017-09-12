package com.intsoft.swachchamaha.fragment;

import android.app.Fragment;
import android.app.VoiceInteractor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intsoft.swachchamaha.R;
import com.intsoft.swachchamaha.adapter.HomePageSliderImageAdapter;
import com.intsoft.swachchamaha.adapter.HomePageSliderStaticImageAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by prafulljoshi on 12/09/17.
 */

public class HomePageStaticImageFragment extends Fragment {
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    /*private int[] sliderImages = new int[] {
            R.drawable.banega_swachh_india, R.drawable.swachcha_maharashtra,
            R.drawable.mahad, R.drawable.vengurla, R.drawable.khopoli
    };*/

    private int[] sliderImages = new int[] {
            R.drawable.scrollimage1, R.drawable.scrollimage2,
            R.drawable.scrollimage3, R.drawable.scrollimage4, R.drawable.scrollimage2
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inaflatedView = inflater.inflate(R.layout.home_page_fragment, container, false);
        final ViewPager sliderImageViewSlider = (ViewPager) inaflatedView.findViewById(R.id.home_page_image_slider);
        CircleIndicator indicator = (CircleIndicator) inaflatedView.findViewById(R.id.indicator);

        HomePageSliderStaticImageAdapter homePageSliderImageAdapter = new HomePageSliderStaticImageAdapter(getActivity(), sliderImages);
        sliderImageViewSlider.setAdapter(homePageSliderImageAdapter);
        indicator.setViewPager(sliderImageViewSlider);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == sliderImages.length) {
                    currentPage = 0;
                }
                sliderImageViewSlider.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        return inaflatedView;
    }
}
