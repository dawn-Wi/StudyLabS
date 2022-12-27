package com.dawn.studylab.ui.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
            .padding(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .background(
                                Color(
                                    0xFF2596BE
                                )
                            )
                            .align(Alignment.CenterVertically),
                    ) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(125.dp),
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Login Logo Image",
                        )
                    }
                }
            }
            Box() {
                Column() {
                    Text(
                        modifier = Modifier,
                        text = "Email",
                        color = Color.Gray
                    )
                    TextInputField(
                        modifier = Modifier.fillMaxWidth(),
                        value = username,
                        onValueChange = { viewModel.onEvent(LoginUiEvent.UsernameChanged(it)) },
                        label = "EMAIL",
                        placeholder = "EMAIL를 입력해주세요",
                        imeAction = ImeAction.Next
                    )
                }
            }
            Box() {
                Column() {
                    Text(
                        modifier = Modifier,
                        text = "Password",
                        color = Color.Gray
                    )
                    PasswordInputField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
                        label = "PASSWORD",
                        placeholder = "PASSWORD를 입력해주세요",
                        imeAction = ImeAction.Done
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                SSButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.onEvent(LoginUiEvent.LoginPressed) },
                    text = "LOGIN",
                )
                SSButton(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO*/ },
                    text = "SIGNUP"
                )
            }
        }
    }
}
