package com.example.wallpaperapp.myapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.wallpaperapp.core.utils.Constants.permissionList
import com.example.wallpaperapp.presentation.components.LocalNavController
import com.example.wallpaperapp.presentation.navigation.Navigation
import com.example.wallpaperapp.utils.AllowPermissionScreen
import com.example.wallpaperapp.utils.FailureScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val permission = rememberMultiplePermissionsState(permissions = permissionList)
            CompositionLocalProvider(value = LocalNavController provides navController) {
                if (permission.allPermissionsGranted) {
                    Navigation(navController = navController)
                } else {
                    AllowPermissionScreen {
                        permission.launchMultiplePermissionRequest()
                    }
                }
            }
        }
    }
}
