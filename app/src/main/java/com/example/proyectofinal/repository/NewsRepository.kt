package com.example.proyectofinal.repository

import com.example.proyectofinal.data.model.NewsModel

interface NewsRepository {
    suspend fun getNotes(): List<NewsModel>;
    suspend fun saveNews(newsModel: NewsModel?): NewsModel?
}