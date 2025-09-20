package com.dv.app.qrcodeftp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dv.app.qrcodeftp.presentation.home.HomeScreen
import com.dv.app.qrcodeftp.presentation.home.HomeViewModel
import com.dv.app.qrcodeftp.presentation.scan.ScanScreen
import com.dv.app.qrcodeftp.presentation.scan.ScanViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Home
    ) {
        composable<Home> {
            val vm = hiltViewModel<HomeViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()
            HomeScreen(
                state,
                onScanNavigation = { navController.navigate(Scan) }
            )
        }

        composable<Scan> {
            val vm = hiltViewModel<ScanViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()
            ScanScreen(state)
        }
    }
}