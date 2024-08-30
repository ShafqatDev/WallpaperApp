package com.example.wallpaperapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.presentation.gallery.GalleryScreen
import com.example.wallpaperapp.presentation.main.components.MainBottomBar
import com.example.wallpaperapp.presentation.wallpaper.WallpaperScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    photoList: (Int,List<Result>) -> Unit = {_,_->}
) {
    val pagerState = rememberPagerState {
        3
    }
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = if (pagerState.currentPage == 0) "Wallpapers" else if (pagerState.currentPage == 1) "Gallery" else "Favourites")
        })
    }, bottomBar = {
        MainBottomBar(selectedTab = pagerState.currentPage, onSelect = { selectedPage ->
            scope.launch {
                pagerState.animateScrollToPage(selectedPage)
            }
        })
    }) { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), state = pagerState
        ) { pages ->
            when (pages) {
                0 -> WallpaperScreen(photoList = photoList)
                1 -> GalleryScreen(photoList = photoList)
            }
        }
    }
}