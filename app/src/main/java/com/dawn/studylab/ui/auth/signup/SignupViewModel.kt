package com.dawn.studylab.ui.auth.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    fun onEvent(event: SignupUiEvent) {
//        when (event) {
//        }
    }
}

sealed class SignupUiEvent {
}