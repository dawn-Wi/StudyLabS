package com.dawn.studylab.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dawn.studylab.navigation.AUTH_NAV_GRAPH_ROUTE
import com.dawn.studylab.navigation.NavGraph
import com.dawn.studylab.navigation.NavScreen
import com.dawn.studylab.ui.auth.login.LoginScreen
import com.dawn.studylab.ui.auth.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(){
    navigation(
        startDestination = NavScreen.Login.route,
        route = AUTH_NAV_GRAPH_ROUTE
    ){
        composable(route = NavScreen.Login.route){
            LoginScreen()
        }
        composable(route = NavScreen.Signup.route){
            SignupScreen()
        }
    }
}