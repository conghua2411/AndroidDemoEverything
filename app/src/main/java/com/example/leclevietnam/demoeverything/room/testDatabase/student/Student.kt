package com.example.leclevietnam.demoeverything.room.testDatabase.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "student")
data class Student(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,

        @Expose
        @SerializedName("name")
        @ColumnInfo(name = "name")
        var name: String,

        @Expose
        @SerializedName("email")
        @ColumnInfo(name = "email")
        var email: String,

        @Expose
        @SerializedName("birthday")
        @ColumnInfo(name = "birthday")
        var birthday: Date,

        @Expose
        @SerializedName("major")
        @ColumnInfo(name = "major")
        var major: String,

        @Expose
        @SerializedName("classId")
        @ColumnInfo(name = "classId")
        var classId: String
)