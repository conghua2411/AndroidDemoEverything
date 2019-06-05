package com.example.leclevietnam.demoeverything.paging.pageKeyed

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.leclevietnam.demoeverything.paging.model.Photo
import javax.inject.Inject

class PhotoDataFactory @Inject constructor(private val photoDataSource: PhotoDataSource) : DataSource.Factory<Int, Photo>() {

    private val mutableLiveData: MutableLiveData<PhotoDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Photo> {
        mutableLiveData.postValue(photoDataSource)
        return photoDataSource as DataSource<Int, Photo>
    }

    fun getMutableLiveData(): MutableLiveData<PhotoDataSource> {
        return mutableLiveData
    }
}