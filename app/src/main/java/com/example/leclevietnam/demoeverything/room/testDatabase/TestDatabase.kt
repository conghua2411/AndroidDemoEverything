package com.example.leclevietnam.demoeverything.room.testDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.leclevietnam.demoeverything.room.testDatabase.student.Student
import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentDAO

@Database(entities = [Student::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TestDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDAO
}