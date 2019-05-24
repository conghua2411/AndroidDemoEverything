package com.example.leclevietnam.demoeverything.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
}