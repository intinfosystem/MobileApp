package com.intsoft.swachchamaha.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.intsoft.swachchamaha.R;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by prafulljoshi on 27/08/17.
 */

public class LanguageChangeFragment extends Fragment {
    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View inaflatedView = inflater.inflate(R.layout.language_change_fragment, container, false);

        final Switch languageSwitch = (Switch) inaflatedView.findViewById(R.id.language_switch);
        languageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.getLogger("LanguageChangeFragment").info("Switch Clicked");
                boolean marathiSelected = languageSwitch.isChecked();
                if(marathiSelected) {
                    Logger.getLogger(this.getClass().getName()).info("Switch Clicked Marathi Selected");
                    String language = "mr";
                    enableLanguageChange(language);
                } else {
                    Logger.getLogger(this.getClass().getName()).info("Switch Clicked English Selected");
                    String language = "en";
                    enableLanguageChange(language);
                }
            }
        });

        return inaflatedView;
    }

    private void enableLanguageChange(String language) {
        Locale currentLocale = new Locale(language, "IN");
        Locale.setDefault(currentLocale);
        Configuration configuration = new Configuration();
        configuration.locale = currentLocale;
        getActivity().getBaseContext().getResources().updateConfiguration(configuration,
                getActivity().getBaseContext().getResources().getDisplayMetrics());

        Intent intent = getActivity().getIntent();
        getActivity().finish();
        intent.putExtra("FROM_MENU", "LANGUAGE_CHANGE");
        startActivity(intent);
    }
}
