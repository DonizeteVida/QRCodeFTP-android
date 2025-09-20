package com.dv.app.qrcodeftp.presentation.xml

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner

@Composable
fun PreviewViewComposable(onCreate: (PreviewView) -> Unit) {
    val lifecycle = LocalLifecycleOwner.current

    AndroidView(::PreviewView) {
        it.setViewTreeLifecycleOwner(lifecycle)
        onCreate(it)
    }
}