package com.example.qrscanner.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrscanner.R
import com.example.qrscanner.room.BarcodeDatabase
import com.example.qrscanner.room.BarcodeRepo
import com.example.qrscanner.viewModel.ScannerViewModel
import com.example.qrscanner.viewModel.ViewModelFactory

@Composable
fun HistoryScreen(
    navController: NavController,
    url : String?
) {
    val context = LocalContext.current
    val database = remember{BarcodeDatabase.getDatabase(context)}
    val repo = remember{BarcodeRepo(database.barcodeDao())}
    val viewModel : ScannerViewModel = viewModel(factory = ViewModelFactory(repo = repo))
    val collectData by viewModel.collectedUsers.collectAsStateWithLifecycle()
    val paddingTop = WindowInsets.statusBars.asPaddingValues()
    val paddingBottom = WindowInsets.navigationBars.asPaddingValues()

    LaunchedEffect(collectData, url) {
        if (url != null && url.isNotBlank()){
            viewModel.checkIfUrlExits(url)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingTop.calculateTopPadding(),
                bottom = paddingBottom.calculateBottomPadding())
            .padding(top = 15.dp, start = 10.dp, end = 10.dp)
    ) {
        Row (
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate("main")
                    }
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "History",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(3f)
            )

            Spacer(Modifier.weight(1f))
        }
        Spacer(Modifier.padding(10.dp))
        LazyColumn {
            items(collectData){data ->
                     HistoryDesign(data,context, viewModel)
            }
        }
    }
}