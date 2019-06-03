package com.example.leclevietnam.demoeverything.retrofit

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leclevietnam.demoeverything.koinDemo.A
import com.example.leclevietnam.demoeverything.retrofit.coroutine.CoroutineService
import com.google.android.gms.tasks.Tasks.await
import kotlinx.coroutines.*

class RetrofitViewModel(private val coroutineService: CoroutineService) : ViewModel() {

    var data: MutableLiveData<List<UserModel>> = MutableLiveData()
    var textData = ObservableField("have no data")
    var job = SupervisorJob()

    val uiScope = CoroutineScope(Dispatchers.IO + job)


    fun fetchData() = runBlocking {
        Log.d("RetrofitActivity", "start")
        val startime = System.currentTimeMillis()
        val userList1 = async { coroutineService.getUser() }
        val userList2 = async { coroutineService.getUser() }

        Log.d("RetrofitActivity", "${userList1.await().await().size} --- ${userList2.await().await().size}")
        val endTime = System.currentTimeMillis()
        Log.d("RetrofitActivity", "time : ${endTime - startime}")

    }

    fun fetchData2() {
        uiScope.launch {
            val userList = async { coroutineService.getUserResponse() }
            Log.d("RetrofitActivity", "hello")

            withContext(Dispatchers.Main) {
                val result = userList.await().await()
                if (result.isSuccessful) {
                    data.value = result.body()
                } else {
                    data.value = ArrayList()
                }
            }
        }

        Log.d("RetrofitActivity", "hello1")
    }


    fun fetchData1() {
        var users: List<UserModel> = ArrayList()
        runBlocking(Dispatchers.IO) {
            Log.d("RetrofitActivity", "getUser - thread : ${Thread.currentThread().name}")
            users = coroutineService.getUser().await()

        }

        Log.d("RetrofitActivity", "setLiveData - thread : ${Thread.currentThread().name}")
        data.value = users
        if (users.isNotEmpty()) {
            textData.set(users.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}