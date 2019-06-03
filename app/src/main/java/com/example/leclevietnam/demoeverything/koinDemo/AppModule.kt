package com.example.leclevietnam.demoeverything.koinDemo

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<HelloRepository> { HelloRepositoryImpl() }

    single(named(name = "AAA")) {A(1, null)}
    single(named(name = "BBB")) {A(2, null)}

    factory { MySimplePresenter(get()) }

    viewModel {(id: String) -> KoinViewModel(get(), a = get(named(name = id)), b = get(named(name = "BBB"))) }

//    scope(named<MyScopeAc>())
}