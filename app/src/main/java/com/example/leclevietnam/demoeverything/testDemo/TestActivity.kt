package com.example.leclevietnam.demoeverything.testDemo

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.leclevietnam.demoeverything.DemoApplication
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityTestBinding
import com.example.leclevietnam.demoeverything.testDemo.di.DaggerTestActivityComponent
import java.util.*
import javax.inject.Inject

class TestActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestBinding

    @Inject
    lateinit var viewModel: TestViewModel


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testActivityComponent = DaggerTestActivityComponent.builder()
                .appComponent((application as DemoApplication).appComponent)
                .build()

        testActivityComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        binding.viewModel = viewModel

        viewModel.event.observe(this, Observer<Event> { t ->
            if (t is TestViewModel.ShowDialogEvent) {
                val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    _, year, month, dayOfMonth ->
                    viewModel.obsTxtBirthDay.set("$dayOfMonth-${month+1}-$year")
                },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH))

                datePickerDialog.show()
            }
        })
    }
}
