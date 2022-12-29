package com.dawn.studylab.ui.auth.signup

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.studylab.model.User
import com.dawn.studylab.navigation.NavScreen
import com.dawn.studylab.repository.UserRepository
import com.dawn.studylab.service.NavService
import com.dawn.studylab.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val snackbarService: SnackbarService,
    private val navService: NavService
) : ViewModel() {

    private val TAG = "SignupScreenViewModel: "

    private val _formState: MutableState<SignupFormState> = mutableStateOf(SignupFormState())
    private val _isFormValid: MutableState<Boolean> = mutableStateOf(false)
    val formState : State<SignupFormState> = _formState
    val isFormValid : State<Boolean> = _isFormValid

    private fun checkIfFormIsValid(){
        val form by formState
        var isVaild = true
        if(form.username.isEmpty()||!form.username.contains("@")){
            isVaild = false
        } else if(form.password1.length<3){
            isVaild = false
        } else if (form.password2.length<3){
            isVaild = false
        } else if (form.displayName.isEmpty()){
            isVaild = false
        } else if (form.phoneNumber.length<10){
            isVaild = false
        } else if(form.password1 != form.password2){
            isVaild = false
        }
        _isFormValid.value = isVaild
    }

    fun onEvent(event: SignupUiEvent) {
        when (event) {

            is SignupUiEvent.UsernameChanged -> {
                _formState.value = _formState.value.copy(
                    username = event.username
                )
            }
            is SignupUiEvent.Password1Changed -> {
                _formState.value = _formState.value.copy(
                    password1 = event.password1
                )
            }
            is SignupUiEvent.Password2Changed -> {
                _formState.value = _formState.value.copy(
                    password2 = event.password2
                )
            }
            is SignupUiEvent.DisplayNameChanged -> {
                _formState.value = _formState.value.copy(
                    displayName = event.displayName
                )
            }
            is SignupUiEvent.PhoneNumberChanged -> {
                _formState.value = _formState.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            SignupUiEvent.SubmitPressed -> {
                viewModelScope.launch {
                    val form by _formState
                    val user = User(
                        id = form.username,
                        password = form.password1,
                        name = form.displayName,
                        phoneNumber = form.phoneNumber,
                        checkIn = "false"

                    )
                    userRepository.trySignup(user).getOrElse { exception ->
                        exception.message?.let {
                            snackbarService.showSnackbar(it, SnackbarDuration.Short)
                            return@launch
                        }
                    }
                    snackbarService.showSnackbar("Successfully Signed up!")
                    navService.navigate(NavScreen.Login)
                }
            }
        }
        checkIfFormIsValid()
    }
}

data class SignupFormState(
    val username: String ="",
    val password1: String ="",
    val password2: String ="",
    val displayName: String ="",
    val phoneNumber: String =""
)

sealed class SignupUiEvent {
    data class UsernameChanged(val username: String): SignupUiEvent()
    data class Password1Changed(val password1: String): SignupUiEvent()
    data class Password2Changed(val password2: String): SignupUiEvent()
    data class DisplayNameChanged(val displayName: String): SignupUiEvent()
    data class PhoneNumberChanged(val phoneNumber: String): SignupUiEvent()
    object SubmitPressed : SignupUiEvent()
}