package com.naigoapps.restaurantmobile;


import android.os.Bundle;
import androidx.preference.EditTextPreference;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        EditTextPreference addressPreference = findPreference("server_address");
        addressPreference.setSummaryProvider((Preference.SummaryProvider<EditTextPreference>) EditTextPreference::getText);
    }

}
