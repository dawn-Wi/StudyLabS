package com.dawn.studylab.navigation

sealed class NavGraph(
    route: String,
    val startDestination: String
) : NavDestination(route){

    object Auth: NavGraph(
        route = "auth_graph",
        startDestination = NavScreen.Login.route
    )
}
