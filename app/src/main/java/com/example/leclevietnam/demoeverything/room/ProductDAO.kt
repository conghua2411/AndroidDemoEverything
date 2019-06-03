package com.example.leclevietnam.demoeverything.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Query("SELECT * FROM product WHERE id= :id")
    fun getProductById(id: Long): Product

    @Query("SELECT * FROM product")
    fun getAllProductList(): List<Product>

    @Query("SELECT * FROM product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product")
    fun getAllProductPaging(): DataSource.Factory<Int, Product>

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun delProduct(product: Product)
}