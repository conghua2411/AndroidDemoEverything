package com.example.leclevietnam.demoeverything.kotlinDemo

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.leclevietnam.demoeverything.room.ProductRepos
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DemoViewModel @Inject constructor(private val obj: ProductRepos) : ViewModel() {

    var count = ObservableField(0)
    var text = ObservableField("a")

    fun counting() {

        Log.d("testInject", "$obj")

        runBlocking {
            GlobalScope.launch {
                repeat(50) {
                    count.set(count.get()!! + 1)

                    if (count.get()!!%5 == 0) {
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

}