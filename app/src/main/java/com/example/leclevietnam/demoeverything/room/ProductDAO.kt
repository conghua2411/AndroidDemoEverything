package com.example.leclevietnam.demoeverything.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Query("SELECT * FROM product WHERE id= :id")
    fun getProductById(id: Long): Product

    @Query("SELECT * FROM product")
    fun getAllProduct(): LiveData<List<Product>>

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun delProduct(product: Product)
}