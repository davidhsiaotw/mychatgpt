package com.example.mychatgpt

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
}

object Forget : Destination {
    override val route: String = "forget"
}

object Verification : Destination {
    override val route: String = "verification"
}

object ChatList : Destination {
    override val route: String = "list"
}

object Chat : Destination {
    override val route: String = "chat"
}