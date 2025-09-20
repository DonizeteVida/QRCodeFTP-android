package com.dv.app.qrcodeftp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dv.app.qrcodeftp.presentation.navigation.MainNavigation
import com.dv.app.qrcodeftp.ui.theme.QRCodeFTPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeFTPTheme {
                MainNavigation()
            }
        }
    }
}