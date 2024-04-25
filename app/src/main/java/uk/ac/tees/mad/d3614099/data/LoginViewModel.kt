package uk.ac.tees.mad.d3614099.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.d3614099.data.rules.Validator
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter

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
                login()
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
            emailError = emailResult.status, passwordError = passwordResult.status
        )

        allResultPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        loginInProgress.value = true

        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                Log.d(TAG, "Inside_login_success")
                Log.d(TAG, "${it.isSuccessful}")

                if (it.isSuccessful) {
                    loginInProgress.value = false
                    ScreenRouter.navigateTo(Screen.HomeScreen)
                }
            }.addOnFailureListener {
                Log.d(TAG, "Inside_login_failure")
                it.localizedMessage?.let { it1 -> Log.d(TAG, it1) }
                loginInProgress.value = false
            }
    }
}