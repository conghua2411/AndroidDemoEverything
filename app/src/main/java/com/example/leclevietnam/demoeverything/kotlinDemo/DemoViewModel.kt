package com.example.leclevietnam.demoeverything.kotlinDemo

import android.graphics.Color
import android.util.AndroidException
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.leclevietnam.demoeverything.room.Product
import com.example.leclevietnam.demoeverything.room.ProductRepos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DemoViewModel @Inject constructor(private val productRepos: ProductRepos) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var count = ObservableField(0)
    var text = ObservableField("a")

    var color = ObservableField<Int>(Color.parseColor("#0000ff"))

    fun counting() {

        // insert data to room
//        val list = arrayListOf(
//                Product(null, "one", 1, 1),
//                Product(null, "two", 2, 2),
//                Product(null, "three", 3, 3),
//                Product(null, "four", 4, 4)
//        )
//
//        compositeDisposable.add(
//                productRepos.insert(list)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe ({
//                            if (it) {
//                                Log.d("Room", "success")
//                            } else {
//                                Log.d("Room", "failed")
//                            }
//                        }, {
//                            Log.d("Room", "Error ${it.localizedMessage}")
//                        })
//        )

        Log.d("testInject", "$productRepos")

        runBlocking {
            GlobalScope.launch {
                repeat(50) {
                    count.set(count.get()!! + 1)

                    if (count.get()!! % 5 == 0) {
                        sayHello()
                    }

                    delay(500L)
                }
            }
        }
    }

    private suspend fun sayHello() {
        delay(1000)
        text.set(text.get() + "hello")
    }

//    private fun insertProduct() {
//        val product = Product(0, "123123", 123123, 123123)
//        productRepos.insert(arrayOf({product}))
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}