package com.dv.app.qrcodeftp.presentation.scan

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Composable
fun ScanScreen(
    state: Int = 0
) {

}

@HiltViewModel
class ScanViewModel @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(0)
}