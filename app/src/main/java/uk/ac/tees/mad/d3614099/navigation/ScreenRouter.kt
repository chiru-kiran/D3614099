package uk.ac.tees.mad.d3614099.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object HomeScreen : Screen("home")
    data object ProfileScreen : Screen("profile")
    data class DetailScreen(val roomId: Int) : Screen("detail/{roomId}") {
        companion object {
            fun createRoute(roomId: Int) = "detail/$roomId"
        }
    }}

object ScreenRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Login)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

    fun navigateToDetailScreen(roomId: Int) {
        currentScreen.value = Screen.DetailScreen(roomId)
    }
}
