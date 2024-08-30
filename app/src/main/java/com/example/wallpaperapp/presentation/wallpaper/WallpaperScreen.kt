package com.example.wallpaperapp.presentation.wallpaper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.data.response.NetworkResponse
import com.example.wallpaperapp.utils.FailureScreen
import com.example.wallpaperapp.utils.IdleScreen
import com.example.wallpaperapp.utils.ImageCard
import com.example.wallpaperapp.utils.LoadingScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun WallpaperScreen(
    photoList: (Int, List<Result>) -> Unit = { _, _ -> },
    viewModel: WallpaperViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState().value

    when (val response = state.wallpaperResponse) {
        is NetworkResponse.Failure -> {
            FailureScreen(failure = response.error ?: "Unknown Error") {
                viewModel.getWallpapers()
            }
        }

        is NetworkResponse.Idle -> {
            IdleScreen()
        }

        is NetworkResponse.Loading -> {
            LoadingScreen()
        }

        is NetworkResponse.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(response.data ?: emptyList()) { index, result ->
                    ImageCard(imageUrl = result.contentSpecific.thumbUrl, onClick = {
                        photoList.invoke(index, response.data ?: emptyList())
                    })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperBottomSheet(
    showBottomSheet: Boolean = false,
    onDismiss: () -> Unit = {},
    onSetHomeScreen: () -> Unit = {},
    onSetLockScreen: () -> Unit = {},
    onSetBothScreens: () -> Unit = {}
) {
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = {
            onDismiss.invoke()
        }, sheetState = rememberModalBottomSheetState(), dragHandle = {
            Icon(Icons.Default.DragHandle, contentDescription = "Drag handle")
        }) {
            WallpaperOptionContent(onSetHomeScreen = {
                onSetHomeScreen.invoke()
            }, onSetLockScreen = {
                onSetLockScreen.invoke()
            }, onSetBothScreens = {
                onSetBothScreens.invoke()
            })
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun WallpaperOptionContent(
    onSetHomeScreen: () -> Unit = {},
    onSetLockScreen: () -> Unit = {},
    onSetBothScreens: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Set Wallpaper", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        WallpaperOptionButton(text = "Home Screen Wallpaper") {
            onSetHomeScreen.invoke()
        }
        WallpaperOptionButton(text = "Lock Screen Wallpaper") {
            onSetLockScreen.invoke()
        }
        WallpaperOptionButton(text = "Both Screens Wallpaper") {
            onSetBothScreens.invoke()
        }
    }
}


@Composable
fun WallpaperOptionButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}