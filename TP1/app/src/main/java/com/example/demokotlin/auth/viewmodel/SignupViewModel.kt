import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class User(
    val email: String = "",
    val pseudo: String = "",
    val password: String = "",
    val cityCode: String = "",
    val city: String = "",
    val phone: String = ""
)

class SignupViewModel : ViewModel() {
    private val _user = mutableStateOf(User())
    val user: State<User> = _user
    var confirmPassword = mutableStateOf("")

    var showDialog = mutableStateOf(false)
    var dialogMessage = mutableStateOf("")

    // Fonction appelée lors du clic sur le bouton de connexion
    fun onSignupCheck() {
        if (user.value.pseudo.isNotEmpty() && user.value.email.isNotEmpty() && user.value.password.isNotEmpty() &&
        user.value.cityCode.isNotEmpty() && user.value.city.isNotEmpty() && user.value.phone.isNotEmpty()) {
            dialogMessage.value = "$user"
            showDialog.value = true
        } else if (!user.value.password.equals(confirmPassword)) {
            dialogMessage.value = "Vos mot de passe ne correspondent pas !"
            showDialog.value = true
        } else {
            dialogMessage.value = "Veuillez renseigner tous les champs afin de vous inscrire"
            showDialog.value = true
        }
    }

    fun updateEmail(email: String) {
        _user.value = _user.value.copy(email = email)
    }

    fun updatePseudo(pseudo: String) {
        _user.value = _user.value.copy(pseudo = pseudo)
    }

    fun updatePassword(password: String) {
        _user.value = _user.value.copy(password = password)
    }

    fun updateCityCode(cityCode: String) {
        _user.value = _user.value.copy(cityCode = cityCode)
    }

    fun updateCity(city: String) {
        _user.value = _user.value.copy(city = city)
    }

    fun updatePhone(phone: String) {
        _user.value = _user.value.copy(phone = phone)
    }
}
