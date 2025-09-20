package com.dv.app.qrcodeftp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.dv.app.qrcodeftp.ui.theme.QRCodeFTPTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Composable
fun HomeScreen(
    state: Int = 0,
    onScanNavigation: () -> Unit
) {
    Scaffold(
        Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onScanNavigation) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        Text("Home", Modifier.padding(it))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    QRCodeFTPTheme {
        HomeScreen(0, {})
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(0)
}