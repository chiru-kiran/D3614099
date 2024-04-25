package uk.ac.tees.mad.d3614099.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.auth.LoginScreen
import uk.ac.tees.mad.d3614099.presentation.auth.SignUpScreen
import uk.ac.tees.mad.d3614099.presentation.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoommateFinderApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = ScreenRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SignUp -> {
                    SignUpScreen()
                }

                is Screen.Login -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }

            }
        }
    }
}