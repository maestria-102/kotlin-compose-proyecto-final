package com.example.proyectofinal.ui.newsdetails


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
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
import com.example.proyectofinal.ui.theme.Purple700

@Composable
fun NewsDetails(navHostController: NavHostController, newsViewModel: NewsViewModel) {
    val context = LocalContext.current;
    val news = newsViewModel.selectedNews.value;

    Scaffold(
        topBar = {
            TopBar(title = "Ver noticia",
                showBackButton = true,
                onBackClick = {
                    navHostController.popBackStack()
                },
                showRefreshButton = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.Black,
                onClick = {
                    val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                   context.startActivity(webIntent);
                }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Web", tint = Color.White)

            }
        }
    ) {



        Column(modifier = Modifier.fillMaxWidth()) {

            if (news.featured) {
                Box(modifier = Modifier.padding(8.dp).clip(shape = RoundedCornerShape(50f)).background(Purple700).align(Alignment.End)) {
                    Text(
                        text = if(news.featured) "Destacada" else "No destacada",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart),
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                }
            }


            AsyncImage(model = news.imageUrl, contentDescription = null, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp))
            Text(text = news.title, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onBackground)
            Text(text = news.summary, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onBackground)
            Text(text = news.newsSite, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.caption, color = MaterialTheme.colors.onBackground)
        }


    }

}
