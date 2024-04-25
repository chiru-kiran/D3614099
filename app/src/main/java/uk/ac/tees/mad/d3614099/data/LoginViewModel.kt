package uk.ac.tees.mad.d3614099.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.d3614099.data.rules.Validator

class LoginViewModel : ViewModel() {
    private val TAG = SignupViewModel::class.simpleName

    var loginUiState = mutableStateOf(LoginUiState())

    var allResultPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvents) {
        when (event) {
            is LoginUIEvents.EmailChange -> {
                loginUiState.value = loginUiState.value.copy(email = event.email)
            }

            is LoginUIEvents.PasswordChange -> {
                loginUiState.value = loginUiState.value.copy(password = event.password)
            }

            is LoginUIEvents.LoginButtonClicked -> {

            }
        }
        validateLoginWithRule()
    }

    private fun validateLoginWithRule() {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allResultPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth
            .getInstance()
    }

}