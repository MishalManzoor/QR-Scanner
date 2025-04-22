package com.example.qrscanner.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrscanner.R
import com.example.qrscanner.preview.CameraPreview
import com.example.qrscanner.room.BarcodeDatabase
import com.example.qrscanner.room.BarcodeRepo
import com.example.qrscanner.room.Data
import com.example.qrscanner.viewModel.ScannerViewModel
import com.example.qrscanner.viewModel.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScannerScreen(navController: NavController) {
    val context = LocalContext.current
    var barcodeValue by remember { mutableStateOf("") }
    val database = remember { BarcodeDatabase.getDatabase(context) }
    val repository = remember { BarcodeRepo(database.barcodeDao()) }
    val viewModel: ScannerViewModel =
        viewModel(factory = ViewModelFactory(repository))
    val coroutineScope = rememberCoroutineScope()
    var isProcessing by remember { mutableStateOf(false) }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }
    LaunchedEffect(key1 = true) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (hasCameraPermission) {
                CameraPreview(
                    onQrCodeDetected = { barcodes, bitmap ->
                        if (!isProcessing && bitmap != null && barcodes.isNotEmpty()) {
                            val barcode = barcodes[0]
                            barcodeValue = barcode.rawValue.toString()

                                isProcessing = true
                                viewModel.checkIfUrlExits(
                                    scannerUrl =  barcodeValue,
                                    onDoesNotExit = {
                                        viewModel.insertData(Data(url = barcodeValue))
                                        viewModel.collectBarcodeData()
                                        navController.navigate("history")

                                        coroutineScope.launch {
                                            delay(3000)
                                        }
                                        isProcessing = false
                                    },
                                )
                        } else {
                            Log.d("BarcodeScanner", "No barcode detected yet (initial)")
                        }
                    }
                )
            } else {
                ActivityCompat.requestPermissions(
                    context as androidx.activity.ComponentActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    100,
                )
            }

            Image(painter = painterResource(R.drawable.baseline_close_24),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .align(alignment = Alignment.TopEnd)
                    .padding(top = 10.dp, end = 10.dp)
                    .clickable {
                        navController.navigate("main")
                    }
            )
        }
    }
}
