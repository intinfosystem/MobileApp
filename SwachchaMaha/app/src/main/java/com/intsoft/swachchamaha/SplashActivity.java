package com.intsoft.swachchamaha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.intsoft.swachchamaha.util.Constants;

import java.util.Locale;
import java.util.logging.Logger;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectLanguage();

        setContentView(R.layout.new_splash);
        Logger.getLogger("SplashActivity").info("In Splash Activity");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.getLogger("SplashActivity").info("In Handler Run for Home Activity");
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 2000);

    }

    private void selectLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_SHARED_PREF, Context.MODE_PRIVATE);
        String language = sharedPreferences.getString(Constants.PREF_LANG, Constants.DEFAULT_LANG);
        Locale currentLocale = new Locale(language, "IN");
        Locale.setDefault(currentLocale);
        Configuration configuration = new Configuration();
        configuration.locale = currentLocale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
