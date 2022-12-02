package com.example.proyectofinal.data.model

data class NewsModel (
    var title: String = "",
    var summary: String = "",
    var imageUrl: String = "",
    var newsSite: String = "",
    var publishedAt: String = "",
    var url: String = "",
    var id: String = "",
    var updatedAt: String = "",
    var featured: Boolean = false
)