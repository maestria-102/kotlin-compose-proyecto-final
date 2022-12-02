package com.example.proyectofinal.data.remote

import android.util.Log
import com.example.proyectofinal.app.FireCollection
import com.example.proyectofinal.core.SingletonHolder
import com.example.proyectofinal.data.model.NewsModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NewsDatasource (
    private val firestoreInstance: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun getAll(): List<NewsModel?> {
        val collectionString = FireCollection.News.collection.toString()
        val collection = firestoreInstance.collection(FireCollection.News.collection.toString())
        return try {
            collection.get().await().documents.map {
                Log.d("NewsDatasource", "getAll: ${it.data}")
                it.toObject(NewsModel::class.java)
            }
        } catch (e: Exception) {
            Log.d("NewsDatasource", "Error getting news: ${e.message}")
            throw e
        }
    }
    suspend fun insert(newsModel: NewsModel) {
        val collection = firestoreInstance.collection("news")
        try {
            collection.add(newsModel).await()
        } catch (e: Exception) {
            Log.d("NewsDatasource", "Error adding news: ${e.message}")
            throw e
        }
    }
}