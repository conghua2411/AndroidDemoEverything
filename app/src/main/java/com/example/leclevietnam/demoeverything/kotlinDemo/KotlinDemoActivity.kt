package com.example.leclevietnam.demoeverything.kotlinDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.leclevietnam.demoeverything.DemoApplication
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityKotlinDemoBinding
import com.example.leclevietnam.demoeverything.room.ProductDatabase
import com.example.leclevietnam.demoeverything.room.ProductRepos
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class KotlinDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKotlinDemoBinding

    @Inject
    lateinit var viewModel: DemoViewModel

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val kotlinActivityComponent = DaggerKotlinActivityComponent.builder()
                .appComponent((application as DemoApplication).appComponent)
                .build()

        kotlinActivityComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin_demo)

//        val productDatabase = Room.databaseBuilder(this, ProductDatabase::class.java, "product.db").build()
//
//        val productRepos = ProductRepos(productDatabase.productDao())
//
//        viewModel = DemoViewModel(productRepos)
//
//        productRepos.getAllProductById()

        binding.viewModel = viewModel

//        viewModel.counting()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
