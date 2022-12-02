package com.example.proyectofinal.ui.components

import android.graphics.drawable.Icon
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit) = {},
    showRefreshButton: Boolean = false,
    onRefreshClick: (() -> Unit) = {}
) {

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
              if (showBackButton) {
                  IconButton(onClick = onBackClick) {
                      Icon(
                          imageVector = Icons.Default.ArrowBack,
                          contentDescription = "Back"
                      )
                  }
              } else {
                  IconButton(onClick = {}) {
                      Icon(
                          imageVector = Icons.Default.List,
                          contentDescription = "List"
                      )
                  }
              }
        } ,
        actions = {
            if (showRefreshButton) {
                IconButton(onClick = onRefreshClick) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }
        }
    )

}
