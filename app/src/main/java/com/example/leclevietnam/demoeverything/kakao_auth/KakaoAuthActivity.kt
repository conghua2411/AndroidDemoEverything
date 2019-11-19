package com.example.leclevietnam.demoeverything.kakao_auth

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.leclevietnam.demoeverything.R
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_kakao_auth.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class KakaoAuthActivity : AppCompatActivity() {

    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_auth)

        btnKakaoSignIn.setOnClickListener {
            callback = SessionCallback()

            Session.getCurrentSession().addCallback(callback)
            if (!Session.getCurrentSession().checkAndImplicitOpen()) {
                Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    private inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Log.e("KakaoAuthActivity", "KakaoAuthActivity : error ${exception.localizedMessage}")
            }
        }

        override fun onSessionOpened() {
            redirectSignupActivity()
        }
    }

    private fun redirectSignupActivity() {
        Log.d("KakaoAuthActivity", "redirectSignupActivity ")

        val requestOptions = ArrayList<String>()
        requestOptions.add("properties.nickname")
        requestOptions.add("properties.profile_image")
        requestOptions.add("properties.thumbnail_image")
        requestOptions.add("kakao_account.account_email")
        requestOptions.add("kakao_account.age_range")
        requestOptions.add("kakao_account.birthday")
        requestOptions.add("kakao_account.gender")

        UserManagement.getInstance().me(requestOptions, object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                Log.d("KakaoAuthActivity", "onSuccess")
                Log.d("KakaoAuthActivity", "onSuccess: ${result?.id} \n Access token: ${Session.getCurrentSession().tokenInfo.accessToken}")

                tvKakaoData.text = "${result?.id.toString()}\n" +
                        "${result?.nickname}\n" +
                        "${Session.getCurrentSession().tokenInfo.accessToken}"
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.e("KakaoAuthActivity", "onSessionClosed")
            }
        })
    }
}
