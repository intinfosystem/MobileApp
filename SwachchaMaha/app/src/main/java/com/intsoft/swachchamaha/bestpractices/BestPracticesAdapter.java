package com.intsoft.swachchamaha.bestpractices;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intsoft.swachchamaha.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by prafulljoshi on 13/09/17.
 */

public class BestPracticesAdapter extends ArrayAdapter<BestPractice> {


    public BestPracticesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BestPractice> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.best_practice_item, null);
        }
        BestPractice bestPractice = getItem(position);
        if(bestPractice != null) {
            TextView title = (TextView) convertView.findViewById(R.id.bestPracticeTitleTextView);
            title.setText(bestPractice.getTitle());

            TextView description = (TextView) convertView.findViewById(R.id.bestPracticeDescTextView);
            description.setText(bestPractice.getDescription());

            TextView monthAndPlace = (TextView) convertView.findViewById(R.id.bestPracticeMonthPlaceTextView);
            monthAndPlace.setText(bestPractice.getMonth() + " " + bestPractice.getYear() + ", " + bestPractice.getPlace());
        }
        return convertView;
    }
}
