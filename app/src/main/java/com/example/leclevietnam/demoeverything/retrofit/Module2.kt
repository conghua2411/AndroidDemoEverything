package com.example.leclevietnam.demoeverything.retrofit

import com.example.leclevietnam.demoeverything.retrofit.coroutine.CoroutineRetrofitInstance
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val module2 = module {
    single { CoroutineRetrofitInstance.getCoroutineService() }
    viewModel { RetrofitViewModel(get()) }
}