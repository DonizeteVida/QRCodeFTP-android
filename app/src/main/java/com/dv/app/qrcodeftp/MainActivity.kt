package com.dv.app.qrcodeftp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import com.dv.app.qrcodeftp.ui.theme.QRCodeFTPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeFTPTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = {

        }) {
            Icon(Icons.Filled.Add, contentDescription = "")
        }
    }) { innerPadding ->

    }
}

@Composable
fun CameraPreview(onCreate: (PreviewView) -> Unit) {
    val lifecycle = LocalLifecycleOwner.current

    AndroidView(::PreviewView) {
        it.setViewTreeLifecycleOwner(lifecycle)
        onCreate(it)
    }
}