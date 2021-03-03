package pl.training.runkeeper.profile.viewmodels

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    fun isEmailValid(text: String) = text.contains("@")

}