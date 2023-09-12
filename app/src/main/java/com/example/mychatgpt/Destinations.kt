package com.example.mychatgpt

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object Start : Destination {
    override val route: String = "start"
}

object Create : Destination {
    override val route: String = "create"
}

object Login : Destination {
    override val route: String = "login"
    const val EMAIL_ADDRESS = "email_address"
    val routeWithArgs = "$route/{$EMAIL_ADDRESS}"
    val arguments = listOf(navArgument(EMAIL_ADDRESS) { type = NavType.StringType })
}

object Forget : Destination {
    override val route: String = "forget"
}

object ChatList : Destination {
    override val route: String = "list"
}

object Chat : Destination {
    override val route: String = "chat"
}