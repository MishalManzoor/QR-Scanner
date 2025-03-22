package com.example.qrscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.qrscanner.history.HistoryScreen
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrscanner.scanner.ScannerScreen
import com.example.qrscanner.main.MainContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main"){
        composable("main") { MainScreen(navController) }
        composable("scanner") { ScannerScreen(navController) }
        composable("history") { HistoryScreen(navController = navController)}
    }
}

@Composable
fun MainScreen(navController : NavController) {
    val view = LocalView.current
    val activity = view.context as? ComponentActivity
    activity?.let{
        WindowCompat.setDecorFitsSystemWindows(it.window, false) // imp
        val windowInsetsController = WindowInsetsControllerCompat(it.window, view)
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    MainContent(navController)
}