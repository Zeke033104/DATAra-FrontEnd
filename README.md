# DATAra

**Machine Learning-Based Real-Time Mobile Data Depletion Prediction System for Prepaid Users.**

Currently in the **Front-End Prototype Phase**. This repository contains the UI layouts, navigation, and visual components. There is no active backend, local database, or ML model integrated yet.

## 🛠️ Prerequisites

To run this project on your local machine, you will need:
- [Android Studio (Jellyfish or newer)](https://developer.android.com/studio)
- JDK 17 (bundled with latest Android Studio)
- Android SDK 34 (configured inside Android Studio)

## 🚀 How to Run

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
   - Click the green **Run 'app'** ▶️ button in the top toolbar (or press `Shift + F10`).

## 📱 Project Stack

- **UI Toolkit:** Jetpack Compose (Material 3)
- **Language:** Kotlin
- **Architecture (Planned):** MVVM with Repository pattern
- **Navigation:** Jetpack Compose Navigation (`NavHost`)

## 🎨 Current Screens Included
- Login & Registration
- Account Recovery & OTP
- Dashboard (with animated stats & data budget placeholder)
- History (with custom drawn Line Chart placeholder)
- Notifications
- Profile & Settings (Dark mode layout)
- CSV Management (Placeholder)
