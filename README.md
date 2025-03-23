# QR Scanner
This is a QR code and barcode scanner application built using Jetpack Compose, ML Kit Barcode Scanning, Room Database, and CameraX. It allows users to scan QR codes and barcodes in real-time and store the scanned values for later access.

## Features

* **Live Barcode Scanning:** Uses CameraX for live camera preview and ML Kit for real-time barcode detection.
* **Multiple Barcode Format Support:** Supports a wide range of barcode formats.
* **Data Storage:** Stores scanned barcode values in a Room database for persistence.
* **History Screen:** Displays a list of previously scanned barcodes.
* **Jetpack Compose UI:** Modern and declarative UI built using Jetpack Compose.
* **Navigation:** Uses Jetpack Navigation for seamless screen transitions.
* **ViewModel:** Uses ViewModel to manage UI-related data in a lifecycle-conscious way.

## Technologies Used

* **Jetpack Compose:** Modern Android UI toolkit.
* **ML Kit Barcode Scanning:** Google's machine learning library for barcode detection.
* **CameraX:** Jetpack library for simplified camera access.
* **Room Database:** Jetpack persistence library for local data storage.
* **Jetpack Navigation:** Navigation component for in-app navigation.
* **Kotlin:** Programming language.
* **ViewModel:** Jetpack ViewModel for managing UI data.

## Architecture

The application follows the Model-View-ViewModel (MVVM) architectural pattern.

* **View:** Jetpack Compose composables represent the UI.
* **ViewModel:** Manages UI-related data and interacts with the data layer.
* **Model:** Represents the data layer, including the Room database.
  
![Screenshot_20250323-143237](https://github.com/user-attachments/assets/0bf032f7-d2e7-4326-bff0-21d873c1843d)
  
![Screenshot_20250323-143228](https://github.com/user-attachments/assets/f6d5487d-839d-4163-96d8-1650cd1fa273)
