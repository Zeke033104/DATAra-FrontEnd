package com.capstone.datara.ui.theme

import androidx.compose.ui.graphics.Color

// ── Brand Colors ─────────────────────────────────────────────────────────────
val PrimaryBlue     = Color(0xFF135DF2)
val PrimaryGreen    = Color(0xFF00A811)
val BrightGreen     = Color(0xFF00E640)
val DangerRed       = Color(0xFFF03A47)
val DangerRedDark   = Color(0xFFD32F2F)
val WarningOrange   = Color(0xFFFFA500)
val AccentPurple    = Color(0xFF6C63FF)

// ── Dark Theme Surfaces (matches reference screenshots) ───────────────────────
// Background: near-black, matching the reference dark mode
val DarkBackground      = Color(0xFF0F0F0F)
// Card / elevated surface: dark gray
val DarkSurface         = Color(0xFF1A1A1A)
// Slightly elevated card variant (dialogs, alt cards)
val DarkSurfaceVariant  = Color(0xFF252525)
// Border / divider
val DarkBorder          = Color(0xFF333333)
val DarkCard            = Color(0xFF1E1E1E)
val DarkDivider         = Color(0xFF2A2A2A)
val DarkOnBackground    = Color(0xFFFFFFFF)
val DarkOnSurface       = Color(0xFFFFFFFF)
val DarkOnSurfaceVariant= Color(0xFFAAAAAA)  // muted/secondary text in dark mode

// ── Light Theme Surfaces (matches reference screenshots) ─────────────────────
// Background: light gray page bg
val LightBackground     = Color(0xFFEBEBEB)
// Card surface: pure white
val LightSurface        = Color(0xFFFFFFFF)
val LightSurfaceVariant = Color(0xFFF0F0F0)
val LightOnBackground   = Color(0xFF0D0D0D)   // near-black text on light bg
val LightOnSurface      = Color(0xFF0D0D0D)
val LightOnSurfaceVariant = Color(0xFF666666) // secondary/muted text in light mode

// ── Shared Text Colors ────────────────────────────────────────────────────────
val ErrorRed            = Color(0xFFFF4444)
val ErrorRedLight       = Color(0xFFFF6B6B)
val SuccessGreen        = PrimaryGreen
val TextMuted           = Color(0xFF9E9E9E)
val TextPlaceholder     = Color(0xFFAAAAAA)

// ── Transparency Helpers ──────────────────────────────────────────────────────
val PrimaryGreenAlpha15 = PrimaryGreen.copy(alpha = 0.15f)
val PrimaryBlueAlpha15  = PrimaryBlue.copy(alpha = 0.15f)
val AccentPurpleAlpha12 = AccentPurple.copy(alpha = 0.12f)
