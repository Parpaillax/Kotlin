package com.example.demokotlin

import LoginViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.demokotlin.auth.LoginFormPage
import com.example.demokotlin.ui.theme.AppBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBackground {
                LoginFormPage(viewModel = LoginViewModel())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppBackground {
        LoginFormPage(viewModel = LoginViewModel())
    }
}