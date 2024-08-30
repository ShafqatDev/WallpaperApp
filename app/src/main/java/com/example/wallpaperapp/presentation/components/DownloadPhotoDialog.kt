package com.example.wallpaperapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DownloadPhotoDialog(
    showDownloadDialog: Boolean = false, onDismiss: () -> Unit, onDownload: () -> Unit
) {
    if (showDownloadDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(
                    onClick = {
                        onDownload()
                        onDismiss()
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE) // Customize color
                    ), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Download", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancel", color = Color.Gray)
                }
            },
            title = {
                Text(
                    text = "Download Photo", fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Do you want to download this photo?", fontSize = 16.sp
                )
            },
            shape = RoundedCornerShape(16.dp), // Rounded corners for the dialog
            containerColor = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        )
    }
}
