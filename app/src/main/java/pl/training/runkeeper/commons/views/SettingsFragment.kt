package pl.training.runkeeper.commons.views

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import pl.training.runkeeper.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}