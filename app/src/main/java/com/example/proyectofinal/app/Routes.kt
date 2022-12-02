package com.example.proyectofinal.app

sealed class Routes (val route: String) {
    object NewsList : Routes("newsList")
    object NewsDetail : Routes("newsDetail")
    object NewsEdit : Routes("NewsEdit")
    object NewsCreate : Routes("NewsCreate")
}
