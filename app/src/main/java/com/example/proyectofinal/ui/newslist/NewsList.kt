package com.example.proyectofinal.ui.newslist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectofinal.core.Resource
import com.example.proyectofinal.data.remote.NewsService
import com.example.proyectofinal.presentation.NewsViewModel
import com.example.proyectofinal.ui.components.TopBar
import androidx.lifecycle.Observer
import com.example.proyectofinal.app.Routes
import com.example.proyectofinal.app.TableColumn
import com.example.proyectofinal.data.model.NewsModel
import com.example.proyectofinal.ui.components.Table
import com.example.proyectofinal.ui.theme.Purple500

@Composable
 fun NewsList(navHostController: NavHostController, newsViewModel: NewsViewModel) {

    Scaffold(
        topBar = {
            TopBar(title = "Listado de noticias",
                showBackButton = false,
                onBackClick = {},
                showRefreshButton = true,
                onRefreshClick = {
                    newsViewModel.getAll()
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Purple500,
                onClick = {
                    navHostController.navigate(Routes.NewsCreate.route)
                }) {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Create", tint = Color.Yellow)

            }
        }
    ) {


        val newsList = newsViewModel.data;
        when (newsViewModel.loading.value) {
                true -> {
                    Log.d("NewsList", "Loading")


                    Column(modifier = Modifier.fillMaxSize(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }

                }
                false -> {
                    Log.d("NewsList", "Not loading")

                    val columns = listOf(
                        TableColumn("Titulo", 0.7f),
                        TableColumn("Sitio", 0.3f)
                    )

                    newsList.let { list ->
                        Table(columns = columns, data = list.value, onRowClick = {
                            Log.d("NewsList", "Item clicked: $it")
                            newsViewModel.selectedNews.value = it
                            navHostController.navigate(Routes.NewsDetail.route)
                        })

                    }

                }
        }

    }
}