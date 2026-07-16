package com.capstone.datara.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Centralized spacing / sizing tokens.
 * Use these instead of hardcoded dp values throughout the app.
 *
 * Example:  Modifier.padding(Spacing.md)  instead of  Modifier.padding(16.dp)
 */
object Spacing {
    val xs  = 4.dp
    val sm  = 8.dp
    val md  = 16.dp
    val lg  = 24.dp
    val xl  = 32.dp
    val xxl = 48.dp
}

/**
 * Standardized border radius / corner radius tokens.
 */
object Radius {
    val xs    = 4.dp    // tiny badge corners
    val sm    = 8.dp    // small chips, tag badges
    val md    = 12.dp   // cards
    val lg    = 16.dp   // large cards / sheets
    val xl    = 20.dp   // dialogs
    val pill  = 24.dp   // buttons (pill shape)
    val round = 50.dp   // fully round (circle approximation for rectangles)
}

/**
 * Standardized elevation tokens.
 */
object Elevation {
    val none   = 0.dp
    val low    = 1.dp
    val medium = 2.dp
    val high   = 4.dp
    val dialog = 8.dp
}
