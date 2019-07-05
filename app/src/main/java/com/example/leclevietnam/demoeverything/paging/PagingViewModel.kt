package com.example.leclevietnam.demoeverything.paging

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.leclevietnam.demoeverything.paging.api.PagingApi
import com.example.leclevietnam.demoeverything.paging.model.Photo
import com.example.leclevietnam.demoeverything.paging.pageKeyed.PhotoDataFactory
import com.example.leclevietnam.demoeverything.room.Product
import com.example.leclevietnam.demoeverything.room.ProductRepos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

class PagingViewModel @Inject constructor(
        private val productRepos: ProductRepos,
        private val pagingApi: PagingApi,
        private val photoDataFactory: PhotoDataFactory) : ViewModel() {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var pagingNavigator: PagingNavigator? = null

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun setNavigator(PagingNavigator: PagingNavigator) {
        this.pagingNavigator = PagingNavigator
    }

    fun loadData() {

        //paging + room
        compositeDisposable.add(
                productRepos.getAllProductPaging()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                            val config = PagedList.Config.Builder()
                                    .setPageSize(20)
                                    .setInitialLoadSizeHint(30)
                                    .setEnablePlaceholders(false)
                                    .build()

//                            pagingNavigator?.updateListPaging(LivePagedListBuilder<Int, Product>(it, 10).build())
                            pagingNavigator?.updateListPaging(LivePagedListBuilder<Int, Product>(it, config).build())
                        }, {
                            Log.d("Paging", "PagingViewModel : error - ${it.localizedMessage}")
                        })
        )

        // paging + retrofit + dataSource
//        val pagedConfig = PagedList.Config.Builder()
//                .setEnablePlaceholders(true)
//                .setInitialLoadSizeHint(10)
//                .setPageSize(20)
//                .build()
//
//        val photoLiveData: LiveData<PagedList<Photo>> = LivePagedListBuilder(photoDataFactory, pagedConfig).build()
//
//        pagingNavigator?.updatePhotos(photoLiveData)

        //
//        compositeDisposable.add(
//                productRepos.getAllProductList()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({
//                            pagingNavigator?.updateList(it)
//                        }, {
//                            Log.d("Paging", "PagingViewModel : error - ${it.localizedMessage}")
//                        })
//        )
    }

    fun addData() {

        val rand = Random(System.currentTimeMillis())

        val products = arrayListOf(
                Product(name = rand.nextInt().toString(), price = rand.nextLong(), numbers = rand.nextLong())
        )

        compositeDisposable.add(
                productRepos.insert(products)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it) {
                                Log.d("Paging", "addData success: ")
                            } else {
                                Log.d("Paging", "addData failed: ")
                            }
                        }, {
                            Log.d("Paging", "addData error : ${it.localizedMessage}")
                        })
        )
    }

    fun deleteProduct(product: Product) {
        compositeDisposable.add(
                productRepos.delProduct(product)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it) {
                                Log.d("Paging", "deleteProduct : success")
                            } else {
                                Log.d("Paging", "deleteProduct : failed")
                            }
                        }, {
                            Log.d("Paging", "deleteProduct : error : ${it.localizedMessage}")
                        })
        )
    }

    fun updateProduct(product: Product) {

        product.price = 111

        compositeDisposable.add(
                productRepos.update(product)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it) {
                                Log.d("Paging", "updateProduct : success")
                            } else {
                                Log.d("Paging", "updateProduct : failed")
                            }
                        }, {
                            Log.d("Paging", "updateProduct : error : ${it.localizedMessage}")
                        })
        )
    }

    fun loadDataApi() {
        getAllPhoto()
    }

    fun getAllPhoto() {
        val call = pagingApi.getAllPhoto()

        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("paging", "loadDataApi + ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                Log.d("paging", "loadDataApi ${response.body()!!.size}: ${response.body()}")
            }
        })
    }

    fun getPhotoId(id: Int) {
        val call = pagingApi.getPhotoId(id)

        call.enqueue(object : Callback<Photo> {
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.d("paging", "loadDataApi + ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                Log.d("paging", "loadDataApi : ${response.body()}")
            }
        })
    }

    fun getAlbum(albumId: Int) {
        val call = pagingApi.getPhotosByAlbum(albumId)

        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.d("paging", "getAlbum error : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                Log.d("paging", "getAlbum success ${response.body()!!.size}: ${response.body()}")
            }
        })
    }

}