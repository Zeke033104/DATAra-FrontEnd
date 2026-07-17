package com.capstone.datara.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ── Dark Color Scheme ─────────────────────────────────────────────────────────
// Matches the reference screenshot: near-black background, dark-gray cards
private val AppDarkColorScheme = darkColorScheme(
    primary            = PrimaryBlue,
    onPrimary          = Color.White,
    secondary          = PrimaryGreen,
    onSecondary        = Color.White,
    tertiary           = BrightGreen,
    onTertiary         = Color.White,
    background         = DarkBackground,         // #0F0F0F — page bg
    onBackground       = DarkOnBackground,       // #FFFFFF — primary text
    surface            = DarkSurface,            // #1A1A1A — cards
    onSurface          = DarkOnSurface,          // #FFFFFF — text on card
    surfaceVariant     = DarkSurfaceVariant,     // #252525 — elevated cards/dialogs
    onSurfaceVariant   = DarkOnSurfaceVariant,   // #AAAAAA — secondary/muted text
    outline            = DarkBorder,             // #333333 — borders/dividers
    error              = DangerRed
)

// ── Light Color Scheme ────────────────────────────────────────────────────────
// Matches the reference screenshot: light-gray background, white cards
private val AppLightColorScheme = lightColorScheme(
    primary            = PrimaryBlue,
    onPrimary          = Color.White,
    secondary          = PrimaryGreen,
    onSecondary        = Color.White,
    tertiary           = BrightGreen,
    onTertiary         = Color.White,
    background         = LightBackground,        // #EBEBEB — page bg
    onBackground       = LightOnBackground,      // #0D0D0D — primary text
    surface            = LightSurface,           // #FFFFFF — cards
    onSurface          = LightOnSurface,         // #0D0D0D — text on card
    surfaceVariant     = LightSurfaceVariant,    // #F0F0F0 — alt cards
    onSurfaceVariant   = LightOnSurfaceVariant,  // #666666 — secondary text
    outline            = Color(0xFFCCCCCC),       // light borders
    error              = DangerRed
)

@Composable
fun DataraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled to keep our custom colors
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> AppDarkColorScheme
        else      -> AppLightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            // Light status bar icons for light mode, dark icons for dark mode
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}
