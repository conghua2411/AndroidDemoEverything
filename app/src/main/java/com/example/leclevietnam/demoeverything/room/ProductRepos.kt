package com.example.leclevietnam.demoeverything.room

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProductRepos @Inject constructor(private val productDAO: ProductDAO) {


    fun insert(products: List<Product>): Observable<Boolean> {
        return Observable.fromCallable {
            productDAO.insertAll(products)
            true
        }
    }

    fun getProductById(id: Long) = Single.fromCallable { productDAO.getProductById(id) }

    fun getAllProductList() = Observable.fromCallable { productDAO.getAllProductList() }

    fun getAllProduct() = Observable.fromCallable { productDAO.getAllProduct() }

    fun getAllProductPaging() = Observable.fromCallable { productDAO.getAllProductPaging() }

    fun update(products: Product): Observable<Boolean> {
        return Observable.fromCallable {
            productDAO.updateProduct(products)
            true
        }
    }

    fun delProduct(products: Product): Observable<Boolean> {
        return Observable.fromCallable {
            productDAO.delProduct(products)
            true
        }
    }
}