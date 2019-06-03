package com.example.leclevietnam.demoeverything.paging.dataSource

import androidx.paging.ItemKeyedDataSource
import com.example.leclevietnam.demoeverything.room.Product

class PagingItemKeyed : ItemKeyedDataSource<Int, Product>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKey(item: Product): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}