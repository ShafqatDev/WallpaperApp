package com.example.wallpaperapp.presentation.photo_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.presentation.components.DownloadPhotoDialog
import com.example.wallpaperapp.presentation.components.LocalNavController
import com.example.wallpaperapp.presentation.main.components.PhotoDetailBottomBar
import com.example.wallpaperapp.presentation.wallpaper.WallpaperBottomSheet
import com.example.wallpaperapp.ui.theme.WallpaperNameColor
import com.example.wallpaperapp.utils.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoDetailScreen(
    index: Int = 0,
    photoList: List<Result>,
) {
    val pagerState = rememberPagerState(initialPage = index) {
        photoList.size
    }
    val navController = LocalNavController.current
    val viewModel: PhotoDetailViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val currentPhotoTitle = photoList[pagerState.currentPage].title
    val showSave = !currentPhotoTitle.endsWith(
        ".jpg", ignoreCase = true
    ) && !currentPhotoTitle.endsWith(".png", ignoreCase = true)
    val currentPhoto = photoList[pagerState.currentPage]
    val isFromGallery =
        currentPhoto.contentSpecific.previewUrl.contains("content://media/external/images/media/")
    val downloadUri = photoList[pagerState.currentPage].contentSpecific.previewUrl.toUri()
    DownloadPhotoDialog(showDownloadDialog = state.showHideDownloadDialog, onDismiss = {
        viewModel.showHideDownloadDialog()
    }) {
        viewModel.downloadWallpaper(downloadUri, fileName = currentPhotoTitle)
    }
    if (state.isLoading) {
        LoadingScreen()
    } else {
        Scaffold(bottomBar = {
            PhotoDetailBottomBar(showSave = showSave, onDownload = {
                viewModel.showHideDownloadDialog()
            }, onSetWallpaper = {
                viewModel.showBottomSheet(result = photoList[pagerState.currentPage])
            })
        }) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    ImageScreen(imageUrl = photoList[page].contentSpecific.previewUrl)
                }
                IconButton(modifier = Modifier.align(Alignment.TopStart), onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    text = photoList[pagerState.currentPage].title,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(WallpaperNameColor)
                        .padding(horizontal = 16.dp, vertical = 15.dp)
                )
            }
        }
    }
    WallpaperBottomSheet(showBottomSheet = state.showBottomSheet, onDismiss = {
        viewModel.hideBottomSheet()
    }, onSetHomeScreen = {
        state.selectedWallpaperUrlResult?.let {
            viewModel.setHomeScreenWallpaper(
                wallpaper = it.contentSpecific.previewUrl.toUri(), isFromGallery = isFromGallery
            )
        }
        viewModel.hideBottomSheet()
    }, onSetLockScreen = {
        state.selectedWallpaperUrlResult?.let {
            viewModel.setLockScreenWallpaper(
                wallpaper = it.contentSpecific.previewUrl.toUri(), isFromGallery = isFromGallery
            )
        }
        viewModel.hideBottomSheet()
    }, onSetBothScreens = {
        state.selectedWallpaperUrlResult?.let {
            viewModel.setBothScreenWallpaper(
                wallpaper = it.contentSpecific.previewUrl.toUri(), isFromGallery = isFromGallery
            )
        }
        viewModel.hideBottomSheet()
    })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageScreen(imageUrl: String) {
    GlideImage(
        modifier = Modifier.fillMaxSize(),
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
