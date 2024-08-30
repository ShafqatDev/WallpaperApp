package com.example.wallpaperapp.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpaperapp.R
import com.example.wallpaperapp.ui.theme.BottomBarColor
import com.example.wallpaperapp.utils.WhiteCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MainBottomBar(modifier: Modifier = Modifier, selectedTab: Int, onSelect: (Int) -> Unit) {
    WhiteCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.sdp), elevation = 8.sdp, round = 0.sdp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomItems(isSelected = selectedTab == 0, onClick = {
                onSelect.invoke(0)
            }, icon = R.drawable.ic_home, text = "Home")
            BottomItems(isSelected = selectedTab == 1, onClick = {
                onSelect.invoke(1)
            }, icon = R.drawable.ic_gallery, text = "Gallery")
        }
    }
}

@Composable
fun PhotoDetailBottomBar(
    modifier: Modifier = Modifier,
    onDownload: () -> Unit = {},
    onSetWallpaper: () -> Unit = {},
    showSave: Boolean = true,
) {
    Row(
        modifier = modifier
            .height(60.sdp)
            .fillMaxWidth()
            .background(BottomBarColor)
            .padding(vertical = 6.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (showSave) {
            BottomItem(icon = Icons.Default.SaveAlt, text = "Save") {
                onDownload.invoke()
            }
        }
        BottomItem(icon = Icons.Default.Wallpaper, text = "Set Wallpaper") {
            onSetWallpaper.invoke()
        }
    }
}


@Composable
fun RowScope.BottomItems(
    isSelected: Boolean, onClick: () -> Unit, icon: Int, text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.sdp, vertical = 4.sdp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.sdp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.sdp))
                Text(
                    text = text, color = Color.Black, fontSize = 8.ssp
                )
            }
        } else {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.sdp),
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .clickable {
            onClick.invoke()
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text, color = Color.White, fontSize = 15.sp
            )
        }
    }
}
