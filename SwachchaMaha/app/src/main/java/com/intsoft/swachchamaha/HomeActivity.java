package com.intsoft.swachchamaha;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ActionProvider;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ShareActionProvider;
import android.widget.Switch;

import com.intsoft.swachchamaha.fragment.HomePageStaticImageFragment;
import com.intsoft.swachchamaha.fragment.LanguageChangeFragment;
import com.intsoft.swachchamaha.util.Constants;

import java.util.Locale;
import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment galleryFragment = new GalleryFragment();
    private Fragment allPracticesFragment = new AllPracticesFragment();
    private Fragment homePageFragment = new HomePageFragment();
    private Fragment homePageStaticImageFragment = new HomePageStaticImageFragment();
    // private ShareActionProvider shareActionProvider;
    private Fragment languageChangeFragment = new LanguageChangeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectLanguage();
        setContentView(R.layout.activity_home);

        // Default Landing Page
        String from_menu_extra = getIntent().getStringExtra("FROM_MENU");
        if(from_menu_extra != null && "LANGUAGE_CHANGE".equals(from_menu_extra)) {
            navigate(R.id.nav_language_change);
        } else {
            navigate(R.id.nav_home);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_activity_home));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Highlighting Default Menu Item here
        navigationView.setCheckedItem(R.id.nav_home);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        getMenuInflater().inflate(R.menu.share_menu, menu);

        MenuItem item = menu.findItem(R.id.action_share);
        //shareActionProvider = (ShareActionProvider) item.getActionProvider();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_share) {
            shareit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareit() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getResources().getString(R.string.share_message_body);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_via)));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        navigate(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigate(int id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        if (id == R.id.nav_home) {
            // Render home page here
            fragmentTransaction
                    .replace(R.id.content, homePageStaticImageFragment)
                    .commit();
        } else /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else*/ if (id == R.id.nav_all_practices) {
            // Show all practices here
            fragmentTransaction
                    .replace(R.id.content, allPracticesFragment)
                    .commit();
        } else if (id == R.id.nav_gallery) {
            fragmentTransaction
                    .replace(R.id.content, galleryFragment)
                    .commit();
        } else /*if (id == R.id.nav_slideshow) {
            fragmentTransaction
                    .replace(R.id.content, galleryFragment)
                    .commit();
        } else */ if (id == R.id.nav_language_change) {
            fragmentTransaction
                    .replace(R.id.content, languageChangeFragment)
                    .commit();
        } else if (id == R.id.nav_share) {
            shareit();
        } else if (id == R.id.nav_send) {

        }
    }
}
