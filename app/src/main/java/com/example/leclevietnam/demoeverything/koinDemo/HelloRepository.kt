package com.example.leclevietnam.demoeverything.koinDemo

interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl : HelloRepository {
    override fun giveHello(): String = "Hello Koin"
}