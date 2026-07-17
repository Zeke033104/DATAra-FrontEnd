package com.capstone.datara.ui.theme

import androidx.compose.ui.graphics.Color

// ── Brand Colors ─────────────────────────────────────────────────────────────
val PrimaryBlue     = Color(0xFF135DF2)
val PrimaryGreen    = Color(0xFF00A811)
val BrightGreen     = Color(0xFF00E640)
val DangerRed       = Color(0xFFF03A47)
val DangerRedDark   = Color(0xFFD32F2F)
val WarningOrange   = Color(0xFFFFA500)

// ── Dark Theme Colors ─────────────────────────────────────────────────────────
val DarkBackground      = Color(0xFF121622)
val DarkSurface         = Color(0xFF1C2133)
val DarkSurfaceAlt      = Color(0xFF13171F)   // Slightly deeper (used in top bars/headers)
val DarkCard            = Color(0xFF1E2233)   // Dialog / elevated card bg
val DarkDivider         = Color(0xFF2A2F45)
val DarkBorder          = Color(0xFF3A3F55)
val DarkOnBackground    = Color(0xFFFFFFFF)
val DarkOnSurface       = Color(0xFFFFFFFF)

// ── Light Theme Colors ────────────────────────────────────────────────────────
val LightBackground     = Color(0xFFF5F5F5)
val LightSurface        = Color(0xFFFFFFFF)
val LightOnBackground   = Color(0xFF121622)
val LightOnSurface      = Color(0xFF121622)

// ── Text Colors ───────────────────────────────────────────────────────────────
val TextPrimary         = Color(0xFF000000)
val TextSecondary       = Color(0xFF555555)
val TextHint            = Color(0xFF888888)
val TextMuted           = Color(0xFF9E9E9E)
val TextPlaceholder     = Color(0xFFAAAAAA)
val TextWhite           = Color(0xFFFFFFFF)

// ── Accent / State Colors ─────────────────────────────────────────────────────
val ErrorRed            = Color(0xFFFF4444)
val ErrorRedLight       = Color(0xFFFF6B6B)
val SuccessGreen        = PrimaryGreen
val UnreadBlue          = Color(0xFFEEF6FF)
val AccentPurple        = Color(0xFF6C63FF)

// ── Transparency Helpers (pre-mixed for common use) ───────────────────────────
val PrimaryGreenAlpha15 = PrimaryGreen.copy(alpha = 0.15f)
val PrimaryBlueAlpha15  = PrimaryBlue.copy(alpha = 0.15f)
val AccentPurpleAlpha12 = AccentPurple.copy(alpha = 0.12f)
