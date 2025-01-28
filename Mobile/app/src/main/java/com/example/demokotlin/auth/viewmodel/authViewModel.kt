package com.example.demokotlin.auth.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.demokotlin.api.RetrofitInstance
import com.example.demokotlin.api.UserApi
import com.example.demokotlin.auth.ForgotPasswordActivity
import com.example.demokotlin.auth.SignupActivity
import com.example.demokotlin.movie.MovieActivity
import kotlinx.coroutines.launch

data class User(
    val email: String = "",
    val pseudo: String = "",
    val password: String = "",
    val cityCode: String = "",
    val city: String = "",
    val phone: String = ""
)

data class Credential(
    val email: String = "",
    val password: String = ""
)

class AuthViewModel : ViewModel() {
    private val userApi = RetrofitInstance.retrofit.create(UserApi::class.java)
    val user = mutableStateOf(User())
    val cred = mutableStateOf(Credential())
    var confirmPassword = mutableStateOf("")

    var showDialog = mutableStateOf(false)
    var dialogMessage = mutableStateOf("")

    // Fonction appelée lors du clic sur le bouton de connexion
    fun onLoginClicked(context: Context, cred: Credential, navController: NavController) {
        if (cred.email.isNotEmpty() && cred.password.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val user = userApi.login(cred)
                    val dataStore = SettingsDataStore(context)
                    dataStore.saveToken(user)
                    dialogMessage.value = "Vous êtes connecté(e) avec succès ${dataStore.readFromDataStore()}"
                    navController.navigate("movies")
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            dialogMessage.value = "Il vous manque des informations de connexion"
        }
        showDialog.value = true
    }

    fun onSignupCheck(user: User, confirmPassword: String) {
        if (!user.password.equals(confirmPassword)) {
            dialogMessage.value = "Vos mot de passe ne correspondent pas !"
        } else if (user.password.isNotEmpty() && user.pseudo.isNotEmpty() && user.email.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    userApi.signup(user)
                    dialogMessage.value = "Inscirption terminée !"
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            dialogMessage.value = "Veuillez renseigner tous les champs afin de vous inscrire"
        }
        showDialog.value = true
    }

    // Fonction appelée lors du clic sur le bouton de connexion
    fun onForgotPasswordCheck() {
        if (user.value.email.isNotEmpty()) {
            dialogMessage.value = "Email de réinitialisation de mot de passe"
        } else {
            dialogMessage.value = "Veuillez renseigner votre email"
        }
        showDialog.value = true
    }

    fun updateEmail(email: String) {
        user.value = user.value.copy(email = email)
    }

    fun updatePseudo(pseudo: String) {
        user.value = user.value.copy(pseudo = pseudo)
    }

    fun updatePassword(password: String) {
        user.value = user.value.copy(password = password)
    }

    fun updateCityCode(cityCode: String) {
        user.value = user.value.copy(cityCode = cityCode)
    }

    fun updateCity(city: String) {
        user.value = user.value.copy(city = city)
    }

    fun updatePhone(phone: String) {
        user.value = user.value.copy(phone = phone)
    }

    fun updateEmailCred(email: String) {
        cred.value = cred.value.copy(email = email)
    }

    fun updatePasswordCred(password: String) {
        cred.value = cred.value.copy(password = password)
    }
}