package com.example.leclevietnam.demoeverything.room.testDatabase.student

import androidx.room.*

@Dao
interface StudentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<Student>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: Student)

    @Query("SELECT * FROM student")
    fun selectAll(): List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun selectById(id: Long?): Student

    @Delete
    fun delete(students: Student)
}