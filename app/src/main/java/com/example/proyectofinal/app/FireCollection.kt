package com.example.proyectofinal.app

sealed class FireCollection(val collection: String) {
    object News : FireCollection("news")
}