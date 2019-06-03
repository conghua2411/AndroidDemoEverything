package com.example.leclevietnam.demoeverything.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityRetrofitBinding
import com.example.leclevietnam.demoeverything.retrofit.rxjava.RxRetrofitInstance
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class RetrofitActivity : AppCompatActivity() {

    lateinit var binding: ActivityRetrofitBinding

    private var txtResult: TextView? = null
    private var btnGetData: Button? = null

    // rx
    private var compositeDisposable: CompositeDisposable? = null

    // coroutine
    private var myJob: Job? = null

    private val viewModel: RetrofitViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit)

        compositeDisposable = CompositeDisposable()

        binding.viewModel = viewModel

        viewModel.data.observe(this, Observer {
            if (it.isEmpty()) {
                Log.d("RetrofitActivity", "LiveData no data")
            } else {
                Log.d("RetrofitActivity", "LiveData have data")
            }
        })

//        rxGetUser()
    }

    private fun rxGetUser() {

        val stringBuilder = StringBuilder()

//        compositeDisposable!!.add(
//                RxRetrofitInstance.getRxService().rxGetUserId(2)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({
//                            for (user in it) {
//                                stringBuilder.append(user.toString() + "\n\n")
//                            }
//                            txtResult!!.text = stringBuilder.toString()
//                        }, {
//                            txtResult!!.text = it.localizedMessage
//                        })
//        )

        compositeDisposable!!.add(
                RxRetrofitInstance.getRxService().rxGetUserIdSingle(4)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            for (user in it) {
                                stringBuilder.append("${user.toString()}\n")
                            }
                            txtResult!!.text = stringBuilder.toString()
                        }, {
                            txtResult!!.text = it.localizedMessage
                        })
        )

    }

    private fun getUser() {
        val service = RetrofitClientInstance.getRetrofitInstance()!!.create(GetDataService::class.java)

        val call = service.getAllUser()

        call.enqueue(object : Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                txtResult!!.text = t.localizedMessage
            }

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {

                val stringBuilder = StringBuilder()

                for (user in response.body()!!) {
                    stringBuilder.append(user.toString() + "\n\n")
                }

                txtResult!!.text = stringBuilder.toString()
            }
        })
    }

    suspend fun doSomeWork() {
        delay(1000)
        Log.d("coroutine", "hello + ${Thread.currentThread().name}")
    }

    fun hello() {
        Log.d("coroutine", "1 + ${Thread.currentThread().name}")
        runBlocking(Dispatchers.IO) {
            doSomeWork()
        }
        Log.d("coroutine", "2 + ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
        myJob?.cancel()
    }

}
