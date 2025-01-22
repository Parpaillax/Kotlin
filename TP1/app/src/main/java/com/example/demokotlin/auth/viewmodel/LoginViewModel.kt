import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var showDialog = mutableStateOf(false)
    var dialogMessage = mutableStateOf("")

    // Fonction appelée lors du clic sur le bouton de connexion
    fun onLoginClicked() {
        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
            dialogMessage.value = "Vous êtes connecté(e) avec succès"
        } else {
            dialogMessage.value = "Il vous manque des informations de connexion"
        }
        showDialog.value = true
    }
}
