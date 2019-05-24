package com.example.leclevietnam.demoeverything.automateViewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.viewpager.widget.ViewPager
import com.example.leclevietnam.demoeverything.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit

class AutoPagerActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var autoPagerAdapter: AutoPagerAdapter

    private lateinit var compositeDisposable: CompositeDisposable

    private var currentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_pager_activity)

        compositeDisposable = CompositeDisposable()

        viewPager = findViewById(R.id.autoViewPager)

        autoPagerAdapter = AutoPagerAdapter(this, Arrays.asList(
                "https://media.wired.com/photos/5a55457ef41e4c2cd9ee6cb5/master/w_2400,c_limit/Doggo-TopArt-104685145.jpg",
                "http://www.dictionary.com/e/wp-content/uploads/2018/05/doggo-300x300.jpg",
                "https://i.ytimg.com/vi/eLLZd7fW244/maxresdefault.jpg"))

        viewPager.adapter = autoPagerAdapter

//        runAutoHandler()

        // rx
        rxRunAuto()
    }

    private fun runAutoHandler() {
        val autoHandler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                if (viewPager.currentItem < autoPagerAdapter.count - 1) {
                    viewPager.currentItem = viewPager.currentItem + 1
                } else {
                    viewPager.currentItem = 0
                }
                autoHandler.postDelayed(this, 2000)
            }
        }

        autoHandler.postDelayed(runnable, 2000)
    }

    //rx
    private fun rxRunAuto() {
        val observable = Observable.interval(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (currentPos < autoPagerAdapter.count - 1) {
                        currentPos++
                    } else {
                        currentPos = 0
                    }
                    viewPager.currentItem = currentPos
                }
        compositeDisposable.add(observable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
