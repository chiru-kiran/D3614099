package uk.ac.tees.mad.d3614099.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.d3614099.R
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.d3614099.data.LoginUIEvents
import uk.ac.tees.mad.d3614099.data.LoginViewModel
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.navigation.SystemBackButtonHandler
import uk.ac.tees.mad.d3614099.presentation.common.ButtonComponent
import uk.ac.tees.mad.d3614099.presentation.common.ClickableLoginTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.DividerTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.HeadingTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponent
import uk.ac.tees.mad.d3614099.presentation.common.PasswordTextFieldComponent
import uk.ac.tees.mad.d3614099.presentation.common.UnderLinedTextComponent

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
) {

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

//                NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvents.EmailChange(it))
                },
//                    errorStatus = loginViewModel.loginUIState.value.emailError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvents.PasswordChange(it))
                },
//                    errorStatus = loginViewModel.loginUIState.value.passwordError
            )

            Spacer(modifier = Modifier.height(20.dp))
            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

//                Spacer(modifier = Modifier.height(5.dp))

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {

                },
                isEnabled = loginViewModel.allResultPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
//                    navController.navigate(Route.SignUpScreen.route)
                ScreenRouter.navigateTo(Screen.SignUp)
            })
        }
    }

//        if(a.loginInProgress.value) {
//            CircularProgressIndicator()

//    SystemBackButtonHandler {
//        ScreenRouter.navigateTo(Screen.SignUpScreen)
//    }
}
//}


//}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}