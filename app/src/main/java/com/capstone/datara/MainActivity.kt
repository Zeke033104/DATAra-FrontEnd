package com.capstone.datara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.datara.ui.screens.AccountRecoveryScreen
import com.capstone.datara.ui.screens.CsvManagementScreen
import com.capstone.datara.ui.screens.DashboardScreen
import com.capstone.datara.ui.screens.DeleteAccountScreen
import com.capstone.datara.ui.screens.DeletedSuccessfullyScreen
import com.capstone.datara.ui.screens.ForgetPassword
import com.capstone.datara.ui.screens.HistoryScreen
import com.capstone.datara.ui.screens.LoginScreen
import com.capstone.datara.ui.screens.NotificationScreen
import com.capstone.datara.ui.screens.OtpScreen
import com.capstone.datara.ui.screens.ProfileScreen
import com.capstone.datara.ui.screens.RegisterScreen
import com.capstone.datara.ui.screens.SettingsScreen
import com.capstone.datara.ui.screens.TermsAndConditionsScreen
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.DarkSurface
import com.capstone.datara.ui.theme.DataraTheme
import com.capstone.datara.ui.theme.PrimaryGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataraTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DataraApp()
                }
            }
        }
    }
}

// ── Reusable transition specs ────────────────────────────────────────────────
private const val ANIM_DURATION = 350

// Forward navigation: slide in from right + fade in
private fun forwardEnter(): EnterTransition =
    slideInHorizontally(animationSpec = tween(ANIM_DURATION)) { it / 3 } +
    fadeIn(animationSpec = tween(ANIM_DURATION))

// Forward navigation: the old screen slides out left + fades out
private fun forwardExit(): ExitTransition =
    slideOutHorizontally(animationSpec = tween(ANIM_DURATION)) { -it / 3 } +
    fadeOut(animationSpec = tween(ANIM_DURATION))

// Pop (back) navigation: slide in from left + fade in
private fun popEnter(): EnterTransition =
    slideInHorizontally(animationSpec = tween(ANIM_DURATION)) { -it / 3 } +
    fadeIn(animationSpec = tween(ANIM_DURATION))

// Pop (back) navigation: old screen slides out right + fades out
private fun popExit(): ExitTransition =
    slideOutHorizontally(animationSpec = tween(ANIM_DURATION)) { it / 3 } +
    fadeOut(animationSpec = tween(ANIM_DURATION))

// Bottom sheet style: slide up + fade in
private fun slideUpEnter(): EnterTransition =
    slideInVertically(animationSpec = tween(ANIM_DURATION)) { it / 2 } +
    fadeIn(animationSpec = tween(ANIM_DURATION))

// Bottom sheet style exit: slide down + fade out
private fun slideDownExit(): ExitTransition =
    slideOutVertically(animationSpec = tween(ANIM_DURATION)) { it / 2 } +
    fadeOut(animationSpec = tween(ANIM_DURATION))

// Tab cross-fade for bottom nav
private fun tabEnter(): EnterTransition =
    fadeIn(animationSpec = tween(300))

private fun tabExit(): ExitTransition =
    fadeOut(animationSpec = tween(300))

@Composable
fun DataraApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarScreens = listOf(
        "dashboard" to Icons.Default.Home,
        "history" to Icons.Default.List,
        "settings" to Icons.Default.Settings
    )
    val showBottomBar = bottomBarScreens.any { it.first == currentRoute }

    Scaffold(
        containerColor = DarkBackground,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = DarkSurface,
                    contentColor = Color.White
                ) {
                    bottomBarScreens.forEach { (route, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = route) },
                            selected = currentRoute == route,
                            onClick = {
                                navController.navigate(route) {
                                    popUpTo("dashboard") { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DarkSurface,
                                selectedTextColor = PrimaryGreen,
                                indicatorColor = PrimaryGreen,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding),
            // Default transitions for all routes
            enterTransition = { forwardEnter() },
            exitTransition = { forwardExit() },
            popEnterTransition = { popEnter() },
            popExitTransition = { popExit() }
        ) {
            // ── AUTHENTICATION FLOW ─────────────────────────────────────────
            composable(
                "login",
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { forwardExit() },
                popEnterTransition = { fadeIn(animationSpec = tween(400)) },
                popExitTransition = { fadeOut(animationSpec = tween(400)) }
            ) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate("dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onSignUpClick = { navController.navigate("register") },
                    onForgetPasswordClick = { navController.navigate("recovery") }
                )
            }

            composable("register") { backStackEntry ->
                val isTermsAccepted by backStackEntry.savedStateHandle.getStateFlow("terms_accepted", false).collectAsState()
                RegisterScreen(
                    isTermsAccepted = isTermsAccepted,
                    onTermsStateChange = { backStackEntry.savedStateHandle["terms_accepted"] = it },
                    onRegisterClick = {
                        navController.navigate("login") { popUpTo("login") { inclusive = true } }
                    },
                    onLoginClick = { navController.popBackStack() },
                    onTermsClick = { navController.navigate("terms") }
                )
            }

            // Terms slides up like a bottom sheet
            composable(
                "terms",
                enterTransition = { slideUpEnter() },
                exitTransition = { slideDownExit() },
                popEnterTransition = { popEnter() },
                popExitTransition = { slideDownExit() }
            ) {
                TermsAndConditionsScreen(
                    onAcceptClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("terms_accepted", true)
                        navController.popBackStack()
                    },
                    onCancelClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("terms_accepted", false)
                        navController.popBackStack()
                    }
                )
            }

            // ── ACCOUNT RECOVERY FLOW ───────────────────────────────────────
            composable("recovery") {
                AccountRecoveryScreen(
                    onBackClick = { navController.popBackStack() },
                    onSendOtpClick = { navController.navigate("otp") }
                )
            }

            composable("otp") {
                OtpScreen(
                    onBackClick = { navController.popBackStack() },
                    onVerifyClick = { navController.navigate("forgetpassword") }
                )
            }

            composable("forgetpassword") {
                ForgetPassword(
                    onBackClick = { navController.popBackStack() },
                    onResetPasswordClick = {
                        navController.navigate("login") { popUpTo("login") { inclusive = true } }
                    }
                )
            }

            // ── MAIN AUTHENTICATED FLOW (bottom nav tabs = cross-fade) ──────
            composable(
                "dashboard",
                enterTransition = { tabEnter() },
                exitTransition = { tabExit() },
                popEnterTransition = { tabEnter() },
                popExitTransition = { tabExit() }
            ) {
                DashboardScreen(
                    onNotificationClick = { navController.navigate("notifications") },
                    onProfileClick = { navController.navigate("profile") }
                )
            }

            composable(
                "history",
                enterTransition = { tabEnter() },
                exitTransition = { tabExit() },
                popEnterTransition = { tabEnter() },
                popExitTransition = { tabExit() }
            ) {
                HistoryScreen(
                    onNotificationClick = { navController.navigate("notifications") },
                    onProfileClick = { navController.navigate("profile") }
                )
            }

            // Notifications slides up
            composable(
                "notifications",
                enterTransition = { slideUpEnter() },
                exitTransition = { slideDownExit() },
                popEnterTransition = { popEnter() },
                popExitTransition = { slideDownExit() }
            ) {
                NotificationScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Profile slides in from right
            composable("profile") {
                ProfileScreen(
                    onBackClick = { navController.popBackStack() },
                    onDeleteAccountClick = { navController.navigate("delete_account") },
                    onSettingsClick = { navController.navigate("settings") }
                )
            }

            // ── SETTINGS & DATA FLOW ────────────────────────────────────────
            composable(
                "settings",
                enterTransition = { tabEnter() },
                exitTransition = { tabExit() },
                popEnterTransition = { tabEnter() },
                popExitTransition = { tabExit() }
            ) {
                SettingsScreen(
                    onBackClick = { navController.popBackStack() },
                    onDeleteAccountClick = { navController.navigate("delete_account") },
                    onCsvManagementClick = { navController.navigate("csv_management") }
                )
            }

            composable("delete_account") {
                DeleteAccountScreen(
                    onBackClick = { navController.popBackStack() },
                    onConfirmDeleteClick = {
                        navController.navigate("deleted_success") { popUpTo(0) }
                    }
                )
            }

            // Success screen fades in
            composable(
                "deleted_success",
                enterTransition = { fadeIn(animationSpec = tween(600)) },
                exitTransition = { fadeOut(animationSpec = tween(400)) }
            ) {
                DeletedSuccessfullyScreen(
                    onBackToLoginClick = {
                        navController.navigate("login") { popUpTo(0) }
                    }
                )
            }

            composable("csv_management") {
                CsvManagementScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
