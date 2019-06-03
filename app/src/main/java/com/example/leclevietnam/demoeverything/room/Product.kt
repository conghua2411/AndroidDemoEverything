package com.example.leclevietnam.demoeverything.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,

        @Expose
        @SerializedName("name")
        @ColumnInfo(name = "name")
        var name: String,

        @Expose
        @SerializedName("price")
        @ColumnInfo(name = "price")
        var price: Long,

        @Expose
        @SerializedName("numbers")
        @ColumnInfo(name = "numbers")
        var numbers: Long
)