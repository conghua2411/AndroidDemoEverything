package com.example.leclevietnam.demoeverything.simInfoDemo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SubscriptionManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.leclevietnam.demoeverything.R
import kotlinx.android.synthetic.main.activity_sim_info.*

class SimInfoActivity : AppCompatActivity() {

    val REQUEST_READ_PHONE_STATE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sim_info)

        btnGetIccid.setOnClickListener { getIccid() }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            tvSimInfo.text = "permission granted"
        } else {
            tvSimInfo.text = "dont have permission"
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_READ_PHONE_STATE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_READ_PHONE_STATE -> {
                if ((grantResults.isNotEmpty()) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    tvSimInfo.text = "permission granted"
                }
            }
        }
    }

    private fun getIccid() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            val sm = getSystemService(SubscriptionManager::class.java)

            val sis = sm.activeSubscriptionInfoList

            if (sis != null) {
                val si = sis[0]

                tvSimInfo.text = si.iccId
            } else {
                tvSimInfo.text = "null"
            }
        }
    }
}
