package com.example.qrscanner

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.qrscanner.history.HistoryScreen
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.qrscanner.gallery.GalleryScreen
import com.example.qrscanner.scanner.ScannerScreen
import com.example.qrscanner.main.MainContent
import com.example.qrscanner.navigation.Gallery
import com.example.qrscanner.navigation.History
import com.example.qrscanner.navigation.Main
import com.example.qrscanner.navigation.Scanner

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigationNew()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationNew(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Main){
        composable<Main> {
            MainScreen(navController = navController)
        }

        composable<Scanner> {
            ScannerScreen(navController = navController)
        }

        composable<Gallery> {
            GalleryScreen(navController = navController)
        }

        composable<History> { backStackEntry ->
            val route : History = backStackEntry.toRoute()

            HistoryScreen(
                navController = navController,
                url = route.url
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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