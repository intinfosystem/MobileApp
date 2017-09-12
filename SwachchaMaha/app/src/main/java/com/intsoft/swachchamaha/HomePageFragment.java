package com.intsoft.swachchamaha;

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

import com.intsoft.swachchamaha.adapter.HomePageSliderImageAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by prafulljoshi on 23/08/17.
 */

public class HomePageFragment extends Fragment {

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    protected int[] sliderImages = new int[] {
            R.drawable.banega_swachh_india, R.drawable.swachcha_maharashtra,
            R.drawable.mahad, R.drawable.vengurla, R.drawable.khopoli
    };

    ViewPager sliderImageViewSlider;
    View inaflatedView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inaflatedView = inflater.inflate(R.layout.home_page_fragment, container, false);
        new NetworkImageFetcher().execute(new String[]{""});
        return inaflatedView;
    }

    class NetworkImageFetcher extends AsyncTask<String, Void, Bitmap[]> {

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Bitmap[] doInBackground(String... params) {
            Bitmap[] sliderBitmaps = new Bitmap[5];
            try {
                // URL imageUrl1 = new URL("http://www.swachhbharaturban.in:8080/sbm/content/Banner/SS_2018_webbanner_1920x6502__SBM_NewsletterTue_Aug_01_17:50:03_IST_2017.jpg");
                URL imageUrl1 = new URL("https://lh3.googleusercontent.com/0LvW6UnyuyS6SKEJR62gdLdLXct4UMSukYArWus1fKwe4q-fPyeEI5acJ4T3GBZ7x4oBImslFHAY-zS6DhpWwTvyywpwDmEEhXoh6Dz1zZVLuAct3nL2rmoNs8v8rEZ_n2GLHe8xdH6h278tlhL_n6MYnRXxndLJ69sWjmWo-fIL9qGWe9cemLcisLD_bI34_FTPs9a7kUuqz2m-ScqTLmrAXI2dfRqu2OjJoK71F9C6VmTHmOZ77ksGtAQeWLdRIevm8BcNvd6So7lnDz6m4OUocpcqk_GrANoD84l9ReU8tyhjkST1RcfY4GO1M3QngpPmb3eLvmT5btccDJ3Eql6tuLX2Q5mU2SLP2II6ks0Cc9UMJsFm_2wkkjzBQOLXlqddolpDZGLKaKELOeuDHUMj22jJeyePpiLOKU2xdpPDQFDEZ2iJDIKqwbi6wYQcxamvUClnKP9FwiD6i6TORDLLlt88fDKQsy9w9j5Ug4j2QdSoZsEtM7SP9Pm6iqdpS19uXzlQH9QN8Cf6XHPiELS9aNvee4sW7afIxHPVox8YWU4WAc-ag9TinI94sOVQduHkkhFqbBgGet6AuHexXrT7mHpiFh6aOio1DnwzraIfVagAnCeegkxfyZ9KTfHL8bb0nx-FGAEMPc2ICKABkKg-dhS-Wn6K7y5vx7hNwcwdIQ=w408-h306-no");
                Bitmap bitmap1 = BitmapFactory.decodeStream(imageUrl1.openConnection().getInputStream());
                sliderBitmaps[0] = bitmap1;

                //URL imageUrl2 = new URL("http://www.swachhbharaturban.in:8080/sbm/content/Banner/Suresh_Raina_web_banners_1Thu_Jul_20_15:31:19_IST_2017.jpg");
                URL imageUrl2 = new URL("https://lh3.googleusercontent.com/IpwIumQ5369SE3JG9tQ_i8IOr1ebK72D5eWI-kUDdLOT74xXMT61Qd3YB8GpUIhLnxOJ_hpVhwyImVLc3qQ0Zsr1xuZrJlCqX5EWy5_ThrUVOychjkl0rgKm6kHrRSggcVmAu3T-RRhrS9FX73G1AYBtG0DbHZ5-o8A6XVHr5BHIRPHgcT1D6HJ0n0xegpSN57VvwKRH_yFWJv3n7JK9RsvWN2kQ3sWehU0CTY2pc2Xmn98Sm0N_2G_OlmU_eTu7KsbUPc8kU-Zdp2ilUbXCUWVVYh430cCmnqVrX4gKse9VXqVTztF4Xxbm7BQkl8WnaT_3fvp-SvJ6lhXh-k-V-ubRHOvJfmGTRb_QBkcek6y6aUmHt_kIlUNfQzXUr1e4TwaUp9H6bJKVOsIGI5V6lIUAKwWLNq_9KW7oPoGpuxtk3pCCWk7o1Q4rn-tUx--IQjrlxpGbSDJKhGPi1gfvsFTZDDeKi7yvthwXP7df4QFdq3c4SMsK2OUx074R-QQZ5LWcN713MzC6QmvkVGQxlCrMF1aob0H4hcAvaukeqoZjTvgGryQRTrZKnvBgUohhAcVdz9B3ZY-wMH8OI55n-pfSDG87iZmZM81PQ6Tr-mEckuVmMm1xXhbyod4MpGSAkItDX3vd_92fAuJ_QBbgRqnCo4rvo0ZtQlZ8WG0P8dcgrQ=w342-h257-no");
                Bitmap bitmap2 = BitmapFactory.decodeStream(imageUrl2.openConnection().getInputStream());
                sliderBitmaps[1] = bitmap2;

                URL imageUrl3 = new URL("https://lh3.googleusercontent.com/lA4hToJ6e7QdE9d7SyGg7nma7v5XiM7sSfz7HRq14iLN3BwagDHx7oD1ovctxcUdfttrF3hx_5wNlZB_KkfP-X_pqyA9sSsL5qu7FB0Sps6qt-R3YoIITkSUiEOb3XXJFwDpq4n1K_L147F2Q2AnY9lprJtPaN_wksEoJJCJnNji0XVSugvHXK-JuiPmTCnqkEtPwP2O4t7gDtG4rd-BJVtu6Yvkw4Xve6Rgaljettoi1BENKBo_cWS2DoU17JiCiH2_NM0nnw0f0yzxP7Sp0WgA4_n-bBlnlK3jt8u7O6LtnIq7H5DkdXs97aNRrUJeCK8jbZguqiitr9skn91fGjK9ynX4v_5ssyIxOl3EhKBEPHqimX0KIKPejZY4BR0jasJnJ7D6gqQ2C5_H9SCZKlHAVqonlNo8S2YS4EiAS8l26cKUTo1gDFLszffOLlflIOAHOnyhcRAK9PkA74orw3zrCQCqG2g_8t5as5JsmbCDbZdgCxPSpH0p6qf1p_wkx0cBq80gbZvUXPw9ZJiQZILLQprlamem2YMSDcvdCvdRWaofLU4Wqe8JnsbOf9PB2rOniLXP7sub4mPJ1qKwFWxyP4OZOUVy3P68n1QaA6kh04kKIN_KwwsDH-skDr_SiqPuJbQvavot5hRvhM31YtAzic5DTFyTnZ-8OQqQp-Qa8Q=w408-h306-no");
                Bitmap bitmap3 = BitmapFactory.decodeStream(imageUrl3.openConnection().getInputStream());
                sliderBitmaps[2] = bitmap3;

                URL imageUrl4 = new URL("https://lh3.googleusercontent.com/27gy-oaoYiXXC_OaA67FgkX1Zh3xNIZHdaYabmD59mgIllEQylMJEPzCEFbulVqJG--moxTBUJcg07AY2DHp5m65NidcyY0EtKwrmnEG8br4b2gsb8DJGStfnZiUG1v-Yxva-tl1lqUTNqWpf_vZJB-RZa1scfV64UyxfGeG2rgSuF2MK8WXEyPOFY_zNlUoOrNPDzzs_1u8WOxQxr5_IZLGWno2VDf4xWDSLtRGcBg5_kLmRdSHEuv3WvBl8zkP0fP9qrJ44WWvGBDSgBaBYP-T9rBBYNEjTbxbpq84FGulmMkRGw2twTlF-fghfK8oSPl7kF7jvXm4_YfAASr5qEjVxFcVhJw43GaT60LwspVsytYB7rT7KYZFcUUPp2vLpSZXiMAvWJYL5f1oJ0zQuI6m5M9utVopOOTFqH1BhiLLsdp0bHPGWY7OObe4GXaawIDEt6ZjJ3rolw7DF4cvY3g4qYvQjE0xxDB6FgeLt_r3S_bDVeAzHCjrKeQcuBj-pArBIUv0Crp7uj_Baw192fN5If9n2Rz_GeAR-bFYVLIBr2J7avL5SwL887FaLq89xgkgyYWRDUyTmMrWa_9jjOo3qpoTvt-W-ocrPQD3QIIx0xx-1Hrcths_nA97xQFThdJhixKxGaafa0ku4umocIyPvyoS1JWQft9U-gnRIBDlfA=w408-h306-no");
                Bitmap bitmap4 = BitmapFactory.decodeStream(imageUrl4.openConnection().getInputStream());
                sliderBitmaps[3] = bitmap4;

                URL imageUrl5 = new URL("https://lh3.googleusercontent.com/W2HHb85W6zapYCKJyegu3NDVEdlNGXpwj514a6RPZoO4NUZ1-FdoSVTKDf3W1saTHSVCDtRL9os7YcE9H1i05dp_INA1rlJ66e7dBqEZ_HQoHnrnH4hzpkqIoj7K8TprOlngnBY8eJKo-dUSTqf2McCGivGN6S0y_MbKEeMtAorz_leS5DjY05rQYDFH3Sh88pMEfrivmjohwvMi08JvuBFfZ0CZX_d6FA8t4IPkvrFxkbwKy2B48r8us7ihwLXr7AcQI5TzCd8hyMvMxhj0VBMtRwjkLQRTzqS415seNzX6549Y56LvTUEsSGMv85M1Rx_jK1BWi_yPwNsTDAiJsNyPAkbHgdeQq8LvqGLCtcPGwgb9stKkRAfEZN2i72nL0S6PjrDGXzsX2uTYgCX-g0mTLYbtqvYBvno7dq7fU-QDmhp9olUZBZefs69k9srEH7u0jEDEJfHa5Vq9r72omZP9OJ2lg09ou3dbiydPEKt8Si9MKRexnbNKZej0yYcjzrzWIY9T5hZlgKKQ_XABnMfoLkia9jT4Zp5cC06GbRtj-EiX25caFKJJ54G3eSRH-o179BHo6bbOUuFWphxSsB9WtWJcsFrAtK4uBUNk2EfKT0doz1awt3vMJw_2DPlkQgkWR5T7ccaU4kChVFE7kiADog7UZBLIpeO6A6ukZnLTGw=w408-h306-no");
                Bitmap bitmap5 = BitmapFactory.decodeStream(imageUrl5.openConnection().getInputStream());
                sliderBitmaps[4] = bitmap5;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return sliderBitmaps;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param sliderBitmaps The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Bitmap[] sliderBitmaps) {
            sliderImageViewSlider = (ViewPager) inaflatedView.findViewById(R.id.home_page_image_slider);
            CircleIndicator indicator = (CircleIndicator) inaflatedView.findViewById(R.id.indicator);
            indicator.setViewPager(sliderImageViewSlider);

            HomePageSliderImageAdapter homePageSliderImageAdapter = new HomePageSliderImageAdapter(getActivity(), sliderImages, sliderBitmaps);
            sliderImageViewSlider.setAdapter(homePageSliderImageAdapter);



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
        }
    }
}


