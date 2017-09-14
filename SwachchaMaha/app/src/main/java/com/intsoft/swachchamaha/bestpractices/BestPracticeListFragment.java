package com.intsoft.swachchamaha.bestpractices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;

import com.intsoft.swachchamaha.R;
import com.intsoft.swachchamaha.fragment.SwipeRefreshListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by prafulljoshi on 13/09/17.
 */

public class BestPracticeListFragment extends SwipeRefreshListFragment {

    private static final String LOG_TAG = BestPracticeListFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.best_practice_refresh_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                Log.i(LOG_TAG, "Refresh menu item selected");

                // We make sure that the SwipeRefreshLayout is displaying it's refreshing indicator
                if (!isRefreshing()) {
                    setRefreshing(true);
                }

                // Start our refresh background task
                initiateRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initiateRefresh();

        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                initiateRefresh();
            }
        });
    }

    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        setColorScheme(R.color.color_scheme_1_1, R.color.color_scheme_1_2,
                R.color.color_scheme_1_3, R.color.color_scheme_1_4);
        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        new FetchBestPracticesTask().execute();
    }

    private void onRefreshComplete(List<BestPractice> bestPractices) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        // TODO Update the adapter, either clear all previous items or just refresh
        // TODO Create Practice Adapter
        // TODO Set it here
        ListAdapter listAdapter = getListAdapter();
        BestPracticesAdapter bestPracticesAdapter = null;
        if(listAdapter == null) {
            // TODO what can be input here?
            bestPracticesAdapter = new BestPracticesAdapter();
            setListAdapter(bestPracticesAdapter);
        } else {
            bestPracticesAdapter = (BestPracticesAdapter) listAdapter;
            // TODO either clear or update the contents
            bestPracticesAdapter.notifyDataSetChanged();
        }

        // Stop the refreshing indicator
        setRefreshing(false);
    }

    class FetchBestPracticesTask extends AsyncTask<Void, Void, List<BestPractice>> {

        static final int TASK_DURATION = 6 * 1000; // 3 seconds

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<BestPractice> doInBackground(Void... params) {
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<BestPractice> bestPractices = new ArrayList<>();
            BestPractice bestPractice = new BestPractice();
            bestPractice.setTitle(UUID.randomUUID().toString());
            bestPractice.setDescription(UUID.randomUUID().toString());
            bestPractices.add(bestPractice);
            return bestPractices;
        }

        @Override
        protected void onPostExecute(List<BestPractice> bestPractices) {
            super.onPostExecute(bestPractices);
            // Tell the Fragment that the refresh has completed
            onRefreshComplete(bestPractices);
        }
    }
}
