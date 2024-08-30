package com.example.wallpaperapp.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.domain.model.Photo
import com.example.wallpaperapp.presentation.photo_detail.PhotoDetailScreen
import com.example.wallpaperapp.presentation.main.MainScreen
import com.example.wallpaperapp.presentation.navigation.components.Screens.*

@androidx.compose.runtime.Composable
fun Navigation(
    navController: NavHostController
) {
    var photoList by remember { mutableStateOf<List<Result>>(emptyList()) }
    var photoIndex by remember { mutableStateOf(0) }
    NavHost(navController = navController, startDestination = MainScreen.name) {
        composable(MainScreen.name) {
            MainScreen(photoList = { index, list ->
                photoList = list
                photoIndex = index
                navController.navigate(PhotoDetailScreen.name)
            })
        }
        composable(PhotoDetailScreen.name) {
            PhotoDetailScreen(
                index = photoIndex,
                photoList = photoList,
            )
        }
    }
}