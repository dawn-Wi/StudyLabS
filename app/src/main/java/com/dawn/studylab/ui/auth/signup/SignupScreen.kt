package com.dawn.studylab.ui.auth.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dawn.studylab.ui.comp.PasswordInputField
import com.dawn.studylab.ui.comp.TextInputField

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password1 = formState.password1
    val password2 = formState.password2
    val displayName = formState.displayName
    val phoneNumber = formState.phoneNumber
    val isFormValid by viewModel.isFormValid

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            Box() {
                Column() {
                    Text(text = "Email")
                    TextInputField(
                        value = username,
                        onValueChange = { viewModel.onEvent(SignupUiEvent.UsernameChanged(it)) },
                        label = "EMAIL",
                        placeholder = "EMAIL을 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
            Box() {
                Column() {
                    Text(text = "Password")
                    PasswordInputField(
                        value = password1,
                        onValueChange = { viewModel.onEvent(SignupUiEvent.Password1Changed(it)) },
                        label = "PASSWORD",
                        placeholder = "PASSWORD를 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
            Box() {
                Column() {
                    Text(text = "Password Check")
                    PasswordInputField(
                        value = password2,
                        onValueChange = { viewModel.onEvent(SignupUiEvent.Password2Changed(it)) },
                        label = "PASSWORD CHECK",
                        placeholder = "PASSWORD를 다시 한 번 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
            Box() {
                Column() {
                    Text(text = "Name")
                    TextInputField(
                        value = displayName,
                        onValueChange ={viewModel.onEvent(SignupUiEvent.DisplayNameChanged(it))},
                        label = "NAME",
                        placeholder = "이름을 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
//            Box() {
//                Column() {
//                    Text(text = "Phone Number")
//                    TextInputField(
//                        value =,
//                        onValueChange =,
//                        label =
//                    )
//                }
//            }
        }
    }
}