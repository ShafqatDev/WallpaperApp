package com.example.wallpaperapp.presentation.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.data.mapper.toResult
import com.example.wallpaperapp.presentation.components.LocalNavController
import com.example.wallpaperapp.presentation.wallpaper.WallpaperBottomSheet
import com.example.wallpaperapp.utils.ImageCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun GalleryScreen(
    photoList: (Int, List<Result>) -> Unit = { _, _ -> },
    viewModel: GalleryViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val state = viewModel.state.collectAsState().value
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(state.photoList) { index, photos ->
            ImageCard(imageUrl = photos.imageUrl.toString(), onClick = {
                val photosList = state.photoList.map { it.toResult() }
                photoList.invoke(index, photosList)
            })
        }
    }
}