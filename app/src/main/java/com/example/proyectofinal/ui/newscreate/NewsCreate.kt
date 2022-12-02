package com.example.proyectofinal.ui.newscreate

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectofinal.app.TableColumn
import com.example.proyectofinal.data.model.NewsModel
import com.example.proyectofinal.presentation.NewsViewModel
import com.example.proyectofinal.ui.components.Table
import com.example.proyectofinal.ui.components.TopBar
import com.example.proyectofinal.ui.theme.Purple500

@Composable
fun NewsCreate(navHostController: NavHostController, newsViewModel: NewsViewModel) {


    val (news, newsSetter) = remember { mutableStateOf(NewsModel()) }

    Scaffold(
        topBar = {
            TopBar(title = "Crear nueva noticia",
                showBackButton = true,
                onBackClick = {
                    navHostController.popBackStack()
                },
                showRefreshButton = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Purple500,
                onClick = {
                    Log.d("NewsCreate", "News: ${news}")
                    newsViewModel.insert(news)
                    Toast.makeText(navHostController.context, "Noticia creada", Toast.LENGTH_SHORT).show()
                    navHostController.popBackStack()

                }) {

                Icon(imageVector = Icons.Default.Done, contentDescription = "Check", tint = Color.White)

            }
        }
    ) {


        when (newsViewModel.loading.value) {
            true -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator()

                }
            }
            false -> {

                Column() {


                    Row(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .toggleable(
                            role = Role.Switch,
                            value = news.featured,
                            onValueChange = {
                                newsSetter(news.copy(featured = it))
                            }
                        )
                    ) {
                        Switch(checked = news.featured, onCheckedChange = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "¿Noticia Destacada?")
                    }


                    TextField(value = news.title, onValueChange = {
                        newsSetter(news.copy(title = it))
                    }, label = { Text("Titulo")}, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())

                    TextField(value = news.summary, singleLine = false ,onValueChange = {
                        newsSetter(news.copy(summary = it))
                    }, label = { Text("Descripción")}, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())

                    TextField(value = news.newsSite, singleLine = true, onValueChange = {
                        newsSetter(news.copy(newsSite = it))
                    }, label = { Text("Canal de Noticias")}, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())

                    TextField(value = news.url, singleLine = true, onValueChange = {
                        newsSetter(news.copy(url = it))
                    }, label = { Text("Url del Sitio")}, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())

                    TextField(value = news.imageUrl, singleLine = true, onValueChange = {
                        newsSetter(news.copy(imageUrl = it))
                    }, label = { Text("Url de la imagen")}, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())

                    AsyncImage(model = news.imageUrl, contentDescription = null, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(200.dp))


                }
            }
        }



    }

   }
