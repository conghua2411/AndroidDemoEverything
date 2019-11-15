package com.example.leclevietnam.demoeverything.googlePay

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class GooglePay {
    companion object {
        private fun getBaseRequest(): JSONObject {
            return JSONObject()
                    .put("apiVersion", 2)
                    .put("apiVersionMinor", 0)
        }

        private fun getTokenizationSpecification(): JSONObject {
            val tokenizationSpecification = JSONObject()
            tokenizationSpecification.put("type", "PAYMENT_GATEWAY")
            tokenizationSpecification.put(
                    "parameters",
                    JSONObject()
                            .put("gateway", "example")
                            .put("gatewayMerchantId", "exampleMerchantId"))
            return tokenizationSpecification
        }

        private fun getAllowedCardNetworks(): JSONArray {
            return JSONArray()
                    .put("AMEX")
                    .put("DISCOVER")
                    .put("INTERAC")
                    .put("JCB")
                    .put("MASTERCARD")
                    .put("VISA")
        }

        private fun getAllowedCardAuthMethods(): JSONArray {
            return JSONArray()
                    .put("PAN_ONLY")
                    .put("CRYPTOGRAM_3DS")
        }

        private fun getBaseCardPaymentMethod(): JSONObject {
            val cardPaymentMethod = JSONObject();
            cardPaymentMethod.put("type", "CARD")
            cardPaymentMethod.put("parameters",
                    JSONObject()
                            .put("allowedAuthMethods", getAllowedCardAuthMethods())
                            .put("allowedCardNetworks", getAllowedCardNetworks()))
            return cardPaymentMethod
        }

        private fun getCardPaymentMethod(): JSONObject {
            val cardPaymentMethod = JSONObject()
            cardPaymentMethod.put("tokenizationSpecification", getTokenizationSpecification())
            return cardPaymentMethod
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun getIsReadyToPayRequest(): Optional<JSONObject> {
            return try {
                val isReadyToPayRequest = getBaseRequest()
                isReadyToPayRequest.put(
                        "allowedPaymentMethods",
                        JSONArray()
                                .put(getBaseCardPaymentMethod())
                )

                Optional.of(isReadyToPayRequest)
            } catch (e: JSONException) {
                Optional.empty()
            }
        }

        private fun getTransactionInfo(): JSONObject {
            val transactionInfo = JSONObject()
            transactionInfo.put("totalPrice", "12.34")
            transactionInfo.put("totalPriceStatus", "FINAL")
            transactionInfo.put("currencyCode", "USD")
            return transactionInfo
        }

        private fun getMerchantInfo(): JSONObject {
            return JSONObject()
                    .put("merchantName", "Example Merchant")
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun getPaymentDataRequest(): Optional<JSONObject> {
            return try {
                val paymentDataRequest = getBaseRequest()
                paymentDataRequest.put("allowedPaymentMethods", JSONArray().put(getCardPaymentMethod()))
                paymentDataRequest.put("transactionInfo", getTransactionInfo())
                paymentDataRequest.put("merchantInfo", getMerchantInfo())
                Optional.of(paymentDataRequest)
            } catch (e: JSONException) {
                Optional.empty()
            }
        }
    }
}