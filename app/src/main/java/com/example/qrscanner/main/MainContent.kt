package com.example.qrscanner.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qrscanner.R
import com.example.qrscanner.ui.theme.Black
import com.example.qrscanner.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Black
        ),
        title = { Text(stringResource(R.string.app_name),
            color = White) },
        actions = {
            Image(painter = painterResource(R.drawable.baseline_history_24),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 10.dp, end = 10.dp)
                    .clickable {
                        navController.navigate("history")
                    }
            )
        },
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
    ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
                    .height(45.dp)
                    .background(
                        Black,
                        shape = RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate("scanner")
                    }
            ) {
                Text(text = "Scan QR",
                    color = White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
    }
}