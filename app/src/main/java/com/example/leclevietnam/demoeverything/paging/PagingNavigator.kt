package com.example.leclevietnam.demoeverything.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.leclevietnam.demoeverything.room.Product

interface PagingNavigator {
    fun updateList(list : List<Product>)

    fun updateListPaging(listData: LiveData<PagedList<Product>>)
}