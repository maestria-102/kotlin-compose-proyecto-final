package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.app.Routes
import com.example.proyectofinal.presentation.NewsViewModel
import com.example.proyectofinal.presentation.NewsViewModelFactory
import com.example.proyectofinal.ui.newscreate.NewsCreate
import com.example.proyectofinal.ui.newsdetails.NewsDetails
import com.example.proyectofinal.ui.newslist.NewsList
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme



class MainActivity : ComponentActivity() {

    private val newsViewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.NewsList.route) {
                        composable(Routes.NewsList.route) {
                            NewsList(navigationController, newsViewModel)
                        }
                        composable(Routes.NewsCreate.route) {
                            NewsCreate(navigationController, newsViewModel)
                        }
                        composable(Routes.NewsDetail.route) {
                            NewsDetails(navigationController, newsViewModel)
                        }
                        composable(Routes.NewsEdit.route) {
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProyectoFinalTheme {
        Greeting("Android")
    }
}