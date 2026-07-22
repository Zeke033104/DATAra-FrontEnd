# DATAra

**Machine Learning-Based Real-Time Mobile Data Depletion Prediction System for Prepaid Users.**

Currently in the **Front-End Prototype Phase**. This repository contains the UI layouts, navigation, and visual components. There is no active backend or ML model integrated yet. Room DB and TensorFlow Lite dependencies are included as placeholders for future integration.

---

## Prerequisites

To run this project on your local machine, you will need:

- [Android Studio (Jellyfish or newer)](https://developer.android.com/studio)
- JDK 17 (bundled with latest Android Studio)
- Android SDK 34 (configured inside Android Studio)

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Zeke033104/DATAra-FrontEnd.git
   ```

2. **Open in Android Studio:**
   - Launch Android Studio.
   - Select **Open** and choose the `DATAra` folder you just cloned.
   - Wait for Gradle to finish syncing (this will download all necessary Compose and AndroidX dependencies).

3. **Run the App:**
   - Connect an Android device (via USB debugging) or start an Android Virtual Device (Emulator).
   - Click the green **Run 'app'** button in the top toolbar or press `Shift + F10`.

---

## Project Stack

| Category | Detail |
|---|---|
| Language | Kotlin 1.9.22 |
| UI Toolkit | Jetpack Compose (Material 3) |
| Compose BOM | 2024.09.00 |
| Compose Compiler | 1.5.8 |
| Navigation | Jetpack Compose Navigation 2.7.7 |
| Architecture (Planned) | MVVM with Repository pattern |
| Min SDK | 26 (Android 8.0 Oreo) |
| Target / Compile SDK | 34 (Android 14) |
| Local DB (Placeholder) | Room 2.6.1 |
| ML Runtime (Placeholder) | TensorFlow Lite 2.14.0 |
| Build System | Gradle 8.2.0 (Kotlin DSL) |

---

## Theming

The app uses a fully custom Material 3 color scheme (`DataraTheme`) with support for both light and dark modes. Dynamic color (Material You) is disabled to preserve the custom palette. The theme adapts the system status bar to match the current mode.

---

## Current Screens

| Screen | Description |
|---|---|
| Splash | Animated launch screen |
| Login | User sign-in |
| Register | New account creation with Terms and Conditions |
| Forgot Password | Password reset entry |
| Account Recovery | Recovery flow |
| OTP | One-time password verification |
| Dashboard | Animated data stats and budget placeholder |
| History | Line chart placeholder for usage history |
| Notifications | In-app notification list |
| Profile | User profile view |
| Settings | App settings with dark mode support |
| CSV Management | Import/export placeholder for data records |
| Delete Account | Account deletion confirmation flow |

---

## Repository Structure

```
DATAra/
├── app/
│   └── src/main/
│       ├── java/com/capstone/datara/
│       │   └── ui/
│       │       ├── screens/     # All composable screen files
│       │       └── theme/       # Color, typography, and theme definitions
│       └── res/                 # Resources (fonts, drawables, values)
├── Figma_prototype/             # Reference Figma design assets
├── build.gradle.kts             # Root build configuration
└── app/build.gradle.kts         # App-level build configuration
```

---

## Version

- **App Version:** 1.0 (versionCode 1)
- **Application ID:** `com.capstone.datara`
