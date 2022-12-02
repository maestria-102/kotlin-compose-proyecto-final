package com.example.proyectofinal.data.remote

import com.example.proyectofinal.data.model.NewsModel
import com.google.firebase.firestore.FirebaseFirestore

class NewsService()  {
    private val datasource = NewsDatasource(FirebaseFirestore.getInstance())
    suspend fun getAll() = datasource.getAll()
    suspend fun insert(newsModel: NewsModel) = datasource.insert(newsModel)
}