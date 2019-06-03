package com.example.leclevietnam.demoeverything.koinDemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityKoinBinding
import com.example.leclevietnam.demoeverything.koinDemo.scope.KoinScopeActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class KoinActivity : AppCompatActivity(), Navigator {

//    val presenter: KoinPresenter by inject()

//    val presenter: MySimplePresenter by inject()

    val viewModel: KoinViewModel by viewModel { parametersOf("AAA")}

    private lateinit var binding: ActivityKoinBinding

    private lateinit var composite: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_koin)

        composite = CompositeDisposable()

        composite.add(
                viewModel.subject
                        .subscribe({
                            Log.d("rxjava", "Event emitted $it")

                            if(it.id == 1) {
                                startActivity(Intent(this, it.activity))
                            }

                        }, {
                            error("Error")
                        })
        )

//        viewModel.PagingNavigator = this

        binding.viewModel = viewModel
    }

    override fun gotoScopeKoinActivity() {
        startActivity(Intent(this, KoinScopeActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        composite.clear()
    }
}
