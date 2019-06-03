package com.example.leclevietnam.demoeverything.koinDemo

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.leclevietnam.demoeverything.MainActivity
import com.example.leclevietnam.demoeverything.koinDemo.scope.KoinScopeActivity
import io.reactivex.subjects.PublishSubject
import java.lang.ref.WeakReference

class KoinViewModel(repo: HelloRepository, a: A, b: A) : ViewModel() {
    val hello = ObservableField("")

    // start activity use PagingNavigator
    var navigator: WeakReference<Navigator>? = null

    // start activity use rxJava
    var subject : PublishSubject<A> = PublishSubject.create()


    init {
        hello.set(repo.giveHello())
        Log.d("AAA", "$a -- $b")
    }

    fun gotoScopeKoin() {
//        if (PagingNavigator != null) {
//            PagingNavigator!!.gotoScopeKoinActivity()
//        }

        // start activity use rxJava
        subject.onNext(A(1, KoinScopeActivity::class.java))
//        subject.onNext(A(1, MainActivity::class.java))
    }
}