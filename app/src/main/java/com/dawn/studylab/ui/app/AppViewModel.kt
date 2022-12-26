package com.dawn.studylab.ui.app

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.studylab.navigation.NavScreen
import com.dawn.studylab.service.NavService
import com.dawn.studylab.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val navService: NavService,
    val snackbarService: SnackbarService,
    val navController: NavHostController
) : ViewModel() {
    val currNavScreen = navService.currNavScreen

    fun onEvent(event: AppUiEvent) {
        when (event) {
            is AppUiEvent.NavbarButtonPressed -> navService.navigate(event.destinationScreen)
            AppUiEvent.LogoutPressed -> snackbarService.showSnackbar("Logout Pressed!")
        }
    }
}

sealed class AppUiEvent {
    data class NavbarButtonPressed(val destinationScreen: NavScreen) : AppUiEvent()
    object LogoutPressed: AppUiEvent()
}