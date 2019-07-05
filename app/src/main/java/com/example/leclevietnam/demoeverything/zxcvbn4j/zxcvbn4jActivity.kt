package com.example.leclevietnam.demoeverything.zxcvbn4j

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityZxcvbn4jBinding
import com.nulabinc.zxcvbn.Zxcvbn

class zxcvbn4jActivity : AppCompatActivity() {

    lateinit var binding: ActivityZxcvbn4jBinding

    lateinit var zxcvbn: Zxcvbn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_zxcvbn4j)

        zxcvbn = Zxcvbn()

        binding.etCheckPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("zxcvbn", "${zxcvbn.measure(s.toString())}")
                binding.txtPasswordError.text = zxcvbn.measure(s.toString()).score.toString()
            }
        })
    }
}
