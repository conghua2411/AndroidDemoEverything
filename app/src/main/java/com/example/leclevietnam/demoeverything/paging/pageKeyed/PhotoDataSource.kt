package com.example.leclevietnam.demoeverything.paging.pageKeyed

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.leclevietnam.demoeverything.paging.api.PagingApi
import com.example.leclevietnam.demoeverything.paging.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class PhotoDataSource @Inject constructor(@Named("paging") val pagingApi: PagingApi) : PageKeyedDataSource<Long, Photo>() {

//    private val networkState = MutableLiveData<>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Photo>) {
        pagingApi.getPhotosByAlbum(1).enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("paging", "PhotoDataSource loadInitial error : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    Log.d("paging", "PhotoDataSource loadInitial success : ${response.body()?.size}")

                    callback.onResult(response.body() as MutableList<Photo>, null, 2L)
                } else {
                    Log.d("paging", "PhotoDataSource loadInitial faild : ${response.body()}")
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Photo>) {
        pagingApi.getPhotosByAlbum(params.key.toInt()).enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("paging", "PhotoDataSource loadAfter error : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {

                if (response.isSuccessful) {
                    Log.d("paging", "PhotoDataSource loadAfter success : ${response.body()!!.size}, key : ${params.key}")

                    val nextKey = if (params.key.toInt() == response.body()!!.size) {
                        null
                    } else {
                        params.key + 1
                    }

                    callback.onResult(response.body() as MutableList<Photo>, nextKey)

                } else {
                    Log.d("paging", "PhotoDataSource loadAfter failed")
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Photo>) {
    }
}