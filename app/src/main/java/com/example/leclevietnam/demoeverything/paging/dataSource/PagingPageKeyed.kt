package com.example.leclevietnam.demoeverything.paging.dataSource

import androidx.paging.PageKeyedDataSource
import com.example.leclevietnam.demoeverything.room.Product

class PagingPageKeyed : PageKeyedDataSource<Int, Product>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
    }
}