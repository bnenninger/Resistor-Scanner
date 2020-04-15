# Resistor-Scanner

Resistor Scanner is an Android app for converting resistor color bands to a value. Currently this works by a person manually entering the color bands. Later goals include using a camera to find a resistor value.

## Project Viewing and Editing

This project is an Android Studio project, and is easiest to open it with Android Studio by selecting the root directory in the "open" menu.

The project is intended for Android SDK 28 (Android 9), and has a minimum SDK level of 24.

To open the app alone, from the .apk file, one must open an android studio virtual machine from a blank or populated project, and then drag and drop the app into the virtual machine window to install it. To run the app, one scrolls or drags up to access the app drawer, and then selects the resistor scanner app.

## Use of the App

To use the manual conversion system, one selects the colors of the bands and each time a color is selected, the output value is automatically updated. To save a value to the history system, one presses the "save to history" button. To see the history, one selects the "history" tab in the bottom bar. To return to the manual converter, one selects the "home" tab.

## Dependencies

- Android SDK
  - Minimum version 24, but intended for version 28 (Android 9)
- Java Development Kit 1.8
- Gradle for building, which is included with the Android Studio install.
- OpenCV is not currently a dependency of the project and is not needed, this may change in future versions.
