package com.dv.app.qrcodeftp.presentation.scan

import androidx.camera.core.ImageProxy
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dv.app.qrcodeftp.framework.AnalysisCameraUseCaseHolder
import com.dv.app.qrcodeftp.framework.CameraController
import com.dv.app.qrcodeftp.presentation.xml.PreviewViewComposable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun ScanScreen(
    state: Int = 0,
    onCameraPreviewReady: (PreviewView) -> Unit
) {
    PreviewViewComposable(
        onCameraPreviewReady
    )
}

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val cameraController: CameraController
) : ViewModel() {
    val state = MutableStateFlow(0)

    private val imageProxyQueue = Channel<ImageProxy>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            imageProxyQueue.consumeEach { processImageProxy(it) }
        }
    }

    fun onCameraPreviewReady(
        previewView: PreviewView
    ) {
        viewModelScope.launch {
            val analysisCameraUseCaseHolder = AnalysisCameraUseCaseHolder(previewView)

            cameraController.start(
                previewView,
                analysisCameraUseCaseHolder
            )

            analysisCameraUseCaseHolder.imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(previewView.context),
                imageProxyQueue::trySend
            )
        }
    }

    private suspend fun processImageProxy(imageProxy: ImageProxy) {
        imageProxy.use {
            println(it)
        }
    }
}