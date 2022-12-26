package com.dawn.studylab.ui.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dawn.studylab.R
import com.dawn.studylab.ui.comp.PasswordInputField
import com.dawn.studylab.ui.comp.SSButton
import com.dawn.studylab.ui.comp.TextInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val formState by viewModel.formState
    val username = formState.username
    val password = formState.password

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "LOGIN",
            )
            Image(
                painter = painterResource(id = R.drawable.loginlogo),
                contentDescription = "Login Logo Image"
            )
            TextInputField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { viewModel.onEvent(LoginUiEvent.UsernameChanged(it)) },
                label = "ID",
                placeholder = "ID를 입력해주세요",
                imeAction = ImeAction.Next
            )
            PasswordInputField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
                label = "PASSWORD",
                placeholder = "PASSWORD를 입력해주세요",
                imeAction = ImeAction.Done
            )
            Row() {
                SSButton(
                    onClick = { viewModel.onEvent(LoginUiEvent.LoginPressed)},
                    text = "LOGIN",
                )
                SSButton(
                    onClick = { /*TODO*/ },
                    text = "SIGNUP"
                )
            }
        }
    }
}
