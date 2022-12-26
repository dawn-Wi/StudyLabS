package com.dawn.studylab.ui.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.studylab.service.NavService
import com.dawn.studylab.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navService: NavService,
    private val snackbarService: SnackbarService,
) : ViewModel() {

    private val TAG = "LoginViewModel: "
    private val _formState: MutableState<LoginFormState> = mutableStateOf(LoginFormState())
    val formState: State<LoginFormState> = _formState
    private val _isFormValid: MutableState<Boolean> = mutableStateOf(false)
    val isFormValid: State<Boolean> = _isFormValid

    private fun checkIfFormIsValid(){
        val form by _formState
        var isValid =true
        if(form.username.isEmpty()||!form.username.contains("@")){
            isValid = false
        }else if(form.password.isEmpty()||form.password.length<=4){
            isValid = false
        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged->{
                _formState.value = _formState.value.copy(
                    username = event.username
                )
                checkIfFormIsValid()
            }
            is LoginUiEvent.PasswordChanged->{
                _formState.value = _formState.value.copy(
                    password = event.password
                )
                checkIfFormIsValid()
            }
            LoginUiEvent.LoginPressed->{
                viewModelScope.launch {
                    if (!isFormValid.value){
                        Log.d(TAG, "onEvent: asdf")
                    }
                }
            }
        }
    }
}

data class LoginFormState(
    val username: String ="",
    val password: String =""
)

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object LoginPressed : LoginUiEvent()
}