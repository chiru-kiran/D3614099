package uk.ac.tees.mad.d3614099.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object Login : Screen()
    data object SignUp : Screen()
    data object HomeScreen : Screen()
}

object ScreenRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Login)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
