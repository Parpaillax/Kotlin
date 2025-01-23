package com.example.demokotlin.auth

import ForgotPasswordViewModele
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demokotlin.ui.theme.AppBackground
import com.example.demokotlin.ui.theme.DialogBox
import com.example.demokotlin.ui.theme.GradientButton
import com.example.demokotlin.ui.theme.TextArea

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                ForgotPasswordFormPage(viewModel = ForgotPasswordViewModele())
            }
        }
    }
}

@Composable
fun ForgotPasswordFormPage(viewModel: ForgotPasswordViewModele) {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(vertical = 60.dp)) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset Password Icon",
                    modifier = Modifier.fillMaxWidth().height(90.dp).width(90.dp),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                TextArea(value = viewModel.email.value, onValueChange = {newText -> viewModel.email.value = newText}, modifier = Modifier.fillMaxWidth(), label = "Email", icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                })
                GradientButton(
                    onClick = {viewModel.onForgotPasswordCheck()},
                    modifier = Modifier.fillMaxWidth(),
                    text = "Changer de mot de passe",
                )
                if (viewModel.showDialog.value) {
                    DialogBox(message = viewModel.dialogMessage.value, onDismiss = { viewModel.showDialog.value = false }, modifier = Modifier.padding(top = 60.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AppBackground {
        ForgotPasswordFormPage(viewModel = ForgotPasswordViewModele())
    }
}