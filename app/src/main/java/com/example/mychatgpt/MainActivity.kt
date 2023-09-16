package com.example.mychatgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mychatgpt.ui.chat.chatlist.ChatListScreen
import com.example.mychatgpt.ui.start.StartScreen
import com.example.mychatgpt.ui.start.create.CreateAccountScreen
import com.example.mychatgpt.ui.start.login.LoginScreen
import com.example.mychatgpt.ui.theme.MyChatGPTTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatGPTTheme {
                MyChatGptApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyChatGptApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    Scaffold() { innerPadding ->
        // TODO: if datastore contains email, make chat list screen as start screen, otherwise start screen
        MyNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MyNavHost(
    navController: NavHostController, modifier: Modifier = Modifier,
    startRoute: String = Start.route
) {
    NavHost(navController = navController, startDestination = startRoute, modifier = modifier) {
        // issue: how to pass object between composable functions in navigation
        // solution?: https://stackoverflow.com/a/67133534

        composable(route = Start.route) {
            StartScreen(onClickNavigate = {
                navController.navigateSingleTopTo(route = it)
            })
        }

        composable(route = Create.route) {
            CreateAccountScreen(onClickNavigate = {
                navController.navigateSingleTopTo(Start.route, it)
            })
        }

        composable(route = Login.routeWithArgs, arguments = Login.arguments) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(Login.EMAIL_ADDRESS) ?: ""
            LoginScreen(email = email, onClickNavigate = {
                navController.navigateSingleTopTo(ChatList.route, route = it)
            })
        }

        composable(route = ChatList.route) {
            ChatListScreen()
        }

        composable(route = Chat.route) {

        }
    }
}

fun NavHostController.navigateSingleTopTo(popUpToRoute: String = "", route: String) =
    this.navigate(route) {
        if (popUpToRoute.isNotBlank())
            popUpTo(popUpToRoute) {
                saveState = true
            }

        launchSingleTop =
            true  // at most one copy of a given destination on the top of the back stack
        restoreState =
            true // determines whether this navigation action should restore any state previously saved by PopUpToBuilder.saveState or the popUpToSaveState attribute
    }

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    MyChatGPTTheme {
        MyChatGptApp()
    }
}