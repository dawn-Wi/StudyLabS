package com.dawn.studylab.ui.auth.signup

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dawn.studylab.ui.comp.PasswordInputField
import com.dawn.studylab.ui.comp.SSButton
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
                .padding(50.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box() {
                Column() {
                    Text(text = "Email", color = Color.Gray, fontSize = 13.sp)
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
                    Text(text = "Password", color = Color.Gray, fontSize = 13.sp)
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
                    Text(text = "Password Check", color = Color.Gray, fontSize = 13.sp)
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
                    Text(text = "Name", color = Color.Gray, fontSize = 13.sp)
                    TextInputField(
                        value = displayName,
                        onValueChange = { viewModel.onEvent(SignupUiEvent.DisplayNameChanged(it)) },
                        label = "NAME",
                        placeholder = "이름을 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
            Box() {
                Column() {
                    Text(text = "Parent's Phone Number", color = Color.Gray, fontSize = 13.sp)
                    TextInputField(
                        value = phoneNumber,
                        onValueChange = { viewModel.onEvent(SignupUiEvent.PhoneNumberChanged(it)) },
                        label = "PARENT'S PHONE NUMBER",
                        placeholder = "보호자 핸드폰 번호를 입력해주세요",
                        imeAction = ImeAction.Done
                    )
                }
            }
            SSButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { viewModel.onEvent(SignupUiEvent.SubmitPressed) },
                text = "SUBMIT",
                enabled = isFormValid
            )
        }
    }
}