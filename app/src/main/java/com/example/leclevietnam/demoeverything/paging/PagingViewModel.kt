package com.example.leclevietnam.demoeverything.paging

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.leclevietnam.demoeverything.room.Product
import com.example.leclevietnam.demoeverything.room.ProductRepos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.random.Random

class PagingViewModel @Inject constructor(private val productRepos: ProductRepos) : ViewModel() {
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

        //paging
        compositeDisposable.add(
                productRepos.getAllProductPaging()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
//                            productlist = LivePagedListBuilder<Int, Product>(it, 10).build()
                            pagingNavigator?.updateListPaging(LivePagedListBuilder<Int, Product>(it, 10).build())
                        }, {
                            Log.d("Paging", "PagingViewModel : error - ${it.localizedMessage}")
                        })
        )

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
                Product(null,rand.nextInt().toString(), rand.nextLong(), rand.nextLong())
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

}