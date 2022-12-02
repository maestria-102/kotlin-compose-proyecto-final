package com.example.proyectofinal.presentation

import com.example.proyectofinal.data.model.NewsModel
import com.example.proyectofinal.data.remote.NewsService
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.proyectofinal.core.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    var loading = mutableStateOf(false)
    var selectedNews = mutableStateOf<NewsModel>(NewsModel())
    val data: MutableState<List<NewsModel>> = mutableStateOf(emptyList())
    private val newsService = NewsService()

    init {
        getAll()
    }

    fun getAll() {
        val newsService = NewsService()
        viewModelScope.launch {
            loading.value = true
            data.value = newsService.getAll() as List<NewsModel>
            loading.value = false
        }
    }

    fun insert(newsModel: NewsModel) {
        val newsService = NewsService()
        viewModelScope.launch {
            loading.value = true
            newsService.insert(newsModel)
            getAll()
            loading.value = false
        }
    }


}


class NewsViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}