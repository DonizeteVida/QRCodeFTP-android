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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dv.app.qrcodeftp.ui.theme.QRCodeFTPTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeFTPTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Home
    ) {
        composable<Home> {
            val vm = hiltViewModel<HomeViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()
            HomeScreen(state)
        }
    }
}

@Serializable
object Home

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(state: Int = 0) {
    Scaffold(
        Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
    ) { innerPadding ->

    }
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(0)
}

@Composable
fun CameraPreview(onCreate: (PreviewView) -> Unit) {
    val lifecycle = LocalLifecycleOwner.current

    AndroidView(::PreviewView) {
        it.setViewTreeLifecycleOwner(lifecycle)
        onCreate(it)
    }
}