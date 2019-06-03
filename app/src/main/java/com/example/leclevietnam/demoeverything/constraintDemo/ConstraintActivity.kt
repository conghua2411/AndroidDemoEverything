package com.example.leclevietnam.demoeverything.constraintDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityConstraintBinding

class ConstraintActivity : AppCompatActivity() {

    lateinit var binding: ActivityConstraintBinding
    lateinit var constraintViewModel: ConstraintViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_constraint)

        constraintViewModel = ConstraintViewModel()

        binding.viewModel = constraintViewModel

        binding.btnAnimation.setOnClickListener {

        }
    }
}
