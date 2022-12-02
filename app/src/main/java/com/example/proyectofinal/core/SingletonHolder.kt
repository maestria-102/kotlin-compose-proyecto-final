package com.example.proyectofinal.core

open class SingletonHolder<out T, in A>(private val creator: (A) -> T) {

    @Volatile
    private var instance: T? = null

    private fun createInstance(args: A): T {
        val newInstance = creator(args)
        instance = newInstance
        return newInstance
    }

    fun getInstanceOrNull(): T? = instance

    fun getInstanceOrCreate(arg: A): T = instance ?: synchronized(this) {
        instance ?: createInstance(arg)
    }



}