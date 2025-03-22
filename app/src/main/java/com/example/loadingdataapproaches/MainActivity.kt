package com.example.loadingdataapproaches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loadingdataapproaches.ui.theme.LoadingDataApproachesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoadingDataApproachesTheme {
                val viewModel = LoadDataApproachViewModel()
                val state by viewModel.loadingState.collectAsStateWithLifecycle()
                LaunchedEffect(true) {
                    viewModel.loadData()
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state) {
                            CircularProgressIndicator()
                        } else {
                            Text(text = "Data Loaded Successfully")
                        }
                    }
                }
            }
        }
    }
}

/**
 * First Approach: load data with LaunchedEffect this give me a full control to when to load data
 * but when the configuration changed it will reload the data
 *
 * Second Approach: load data within viewModel init{} block this will solve the configuration changes
 * issue but this will lead to issue when I test the ViewModel because the data will loaded as I create
 * the ViewModel instance, and I want to test something before I load the data
 *
 * Last Approach: is to use flow onStart method to load the data this will keep the data even with
 * configuration changes with WhileSubscribe(times)
 *
 */














