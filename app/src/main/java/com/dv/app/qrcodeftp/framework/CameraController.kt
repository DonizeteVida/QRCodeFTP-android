package com.dv.app.qrcodeftp.framework

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.impl.StreamUseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.concurrent.futures.await
import androidx.lifecycle.findViewTreeLifecycleOwner
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraController @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    suspend fun start(
        view: PreviewView,
        cameraUseCaseHolder: CameraUseCaseHolder
    ) {
        val provider = ProcessCameraProvider
            .getInstance(context)
            .await()

        provider.unbindAll()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        provider.bindToLifecycle(
            view.findViewTreeLifecycleOwner()!!,
            cameraSelector,
            *cameraUseCaseHolder.usesCases
        )
    }
}

interface CameraUseCaseHolder {
    val usesCases: Array<androidx.camera.core.UseCase>
}

class AnalysisCameraUseCaseHolder(
    private val previewView: PreviewView
) : CameraUseCaseHolder {
    private val preview = Preview
        .Builder()
        .build()
        .also { it.surfaceProvider = previewView.surfaceProvider }

    @SuppressLint("RestrictedApi")
    val imageAnalysis = ImageAnalysis
        .Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
        .setImageQueueDepth(60)
        .setStreamUseCase(StreamUseCase.STILL_CAPTURE)
        .build()

    override val usesCases = arrayOf(
        preview,
        imageAnalysis
    )
}