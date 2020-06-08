package com.kamil184.newmotivate.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.kamil184.newmotivate.R;

public class PreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey);
    }
}
