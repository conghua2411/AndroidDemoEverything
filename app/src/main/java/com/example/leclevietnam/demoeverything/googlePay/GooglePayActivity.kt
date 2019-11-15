package com.example.leclevietnam.demoeverything.googlePay

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.leclevietnam.demoeverything.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.*
import com.google.android.gms.wallet.AutoResolveHelper.RESULT_ERROR
import org.json.JSONObject
import java.util.*

class GooglePayActivity : AppCompatActivity() {

    private val LOAD_PAYMENT_DATA_REQUEST_CODE: Int = 42
    private lateinit var mPaymentClient: PaymentsClient

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_pay)

        mPaymentClient = Wallet.getPaymentsClient(
                this,
                Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .build()
        )

        possiblyShowGooglePayButton()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun possiblyShowGooglePayButton() {
        val isReadyToPayJson: Optional<JSONObject> = GooglePay.getIsReadyToPayRequest()
        if (!isReadyToPayJson.isPresent) {
            return
        }
        val request: IsReadyToPayRequest = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString())
                ?: return

        val task: Task<Boolean> = mPaymentClient.isReadyToPay(request)
        task.addOnCompleteListener {
            try {
                val result = task.getResult(ApiException::class.java)

                if (result!!) {
                    findViewById<Button>(R.id.btnGooglePay).setOnClickListener {
                        requestPayment(it)
                    }
                }

            } catch (e: ApiException) {
                Log.e("ApiException", "aloooooo: ${e.localizedMessage}")
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun requestPayment(view: View?) {
        val paymentDataRequestJson: Optional<JSONObject> = GooglePay.getPaymentDataRequest()
        if (!paymentDataRequestJson.isPresent) {
            return
        }

        val request: PaymentDataRequest? =
                PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString())
        if (request != null) {
            AutoResolveHelper.resolveTask(
                    mPaymentClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val paymentData = PaymentData.getFromIntent(data!!)

                        val json = paymentData?.toJson()

                        val paymentMethodData = JSONObject(json)
                                .getJSONObject("paymentMethodData")

                        val paymentToken = paymentMethodData
                                .getJSONObject("tokenizationData")
                                .getString("token")
                    }
                    RESULT_ERROR -> {
                        val status = AutoResolveHelper.getStatusFromIntent(data)
                        Log.e("RESULT_ERROR", "Error : $status")
                    }
                }
            }
        }
    }
}
