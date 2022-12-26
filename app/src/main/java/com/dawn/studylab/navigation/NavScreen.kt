package com.dawn.studylab.navigation

sealed class NavScreen(
    route: String,
    val displayName: String = "",
    val shouldShowNavBars: Boolean = true,
    val parentRoute: String? = null,
): NavDestination(route){

    object Login : NavScreen(
        route = "login_screen",
        displayName = "로그인",
        shouldShowNavBars = false
    )

}
