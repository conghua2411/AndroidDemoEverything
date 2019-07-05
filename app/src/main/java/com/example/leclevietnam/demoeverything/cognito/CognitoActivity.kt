package com.example.leclevietnam.demoeverything.cognito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.amazonaws.mobile.client.*
import com.example.leclevietnam.demoeverything.R
import java.lang.Exception
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.client.results.Tokens
import com.amazonaws.mobileconnectors.cognitoauth.Auth
import com.amazonaws.mobileconnectors.cognitoauth.AuthUserSession
import com.amazonaws.mobileconnectors.cognitoauth.handlers.AuthHandler
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
import com.amazonaws.regions.Regions
import io.reactivex.Observable


class CognitoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cognito)

        // new
        val builder: Auth.Builder = Auth.Builder().setAppClientId("4g9ejtj8oh87vq4mdqdhn0ns2j")
                .setAppClientSecret("1uf6e4tm4h4kefrii3tsq8928jde0k1q03fqkevhq2u61kcbvbc6")
                .setApplicationContext(applicationContext)
                .setAuthHandler(object : AuthHandler {
                    override fun onSuccess(session: AuthUserSession?) {
                        Log.d("AuthBuilder", "sign in onSuccess")
                    }

                    override fun onFailure(e: Exception?) {
                        Log.d("AuthBuilder", "sign in onFailure")
                    }

                    override fun onSignout() {
                        Log.d("AuthBuilder", "sign in onSignout")
                    }
                })

//        val userPool = CognitoUserPool(applicationContext, "ap-southeast-1_GwZ4hQOVY", "4g9ejtj8oh87vq4mdqdhn0ns2j", "1uf6e4tm4h4kefrii3tsq8928jde0k1q03fqkevhq2u61kcbvbc6", Regions.AP_SOUTHEAST_1)
//
//        var cognitoUser: CognitoUser? = null
//
//        var currSession: CognitoUserSession? = null

//        var cognitoDevice: CognitoDevice?
//
//        var multiFactorAuthenticationContinuation: MultiFactorAuthenticationContinuation?

//        val authenticationHandler = object : AuthenticationHandler {
//            override fun onSuccess(userSession: CognitoUserSession?, newDevice: CognitoDevice?) {
//                Log.d("authenticationHandler", "susccess")
//                currSession = userSession
//                cognitoDevice = newDevice
//
//                // launch user
//
//                Log.d("authenticationHandler", "token : ${currSession?.idToken?.jwtToken}")
//
//            }
//
//            override fun onFailure(exception: Exception?) {
//                Log.d("authenticationHandler", "error")
//            }
//
//            override fun getAuthenticationDetails(authenticationContinuation: AuthenticationContinuation?, userId: String?) {
//                Log.d("authenticationHandler", "getAuthenticationDetails")
//
//                val authenticationDetails = AuthenticationDetails("conghua2411@yopmail.com", "123456", null)
//
//                authenticationContinuation?.setAuthenticationDetails(authenticationDetails)
//
//                authenticationContinuation?.continueTask()
//            }
//
//            override fun authenticationChallenge(continuation: ChallengeContinuation?) {
//                Log.d("authenticationHandler", "authenticationChallenge")
//            }
//
//            override fun getMFACode(continuation: MultiFactorAuthenticationContinuation?) {
//                Log.d("authenticationHandler", "getMFACode")
//                multiFactorAuthenticationContinuation = continuation
//
//            }
//        }

        AWSMobileClient.getInstance().initialize(applicationContext, object : Callback<UserStateDetails> {
            override fun onResult(userStateDetails: UserStateDetails) {
                Log.d("CognitoActivity", "init onResult: " + userStateDetails.userState)
            }

            override fun onError(e: Exception) {
                Log.e("CognitoActivity", "init error.", e)
            }
        })

        AWSMobileClient.getInstance().addUserStateListener {
            when (it.userState) {
                UserState.GUEST -> Log.d("CognitoActivity", "user is in guest mode")
                UserState.SIGNED_OUT -> Log.d("CognitoActivity", "user is sign out")
                UserState.SIGNED_IN -> Log.d("CognitoActivity", "user is sign in")
                UserState.SIGNED_OUT_USER_POOLS_TOKENS_INVALID ->
                    Log.d("CognitoActivity", "user need to login again")
                UserState.SIGNED_OUT_FEDERATED_TOKENS_INVALID ->
                    Log.d("CognitoActivity", "user logged in via federation, but currently needs new tokens")
                else -> Log.d("CognitoActivity", "unsupported")
            }
        }

        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            AWSMobileClient.getInstance().signIn("conghua2411@yopmail.com", "123456", null, object : Callback<SignInResult> {
                override fun onResult(result: SignInResult?) {
                    Log.d("CognitoActivity", "signin result : ${result?.signInState}")

                }

                override fun onError(e: Exception?) {
                    Log.e("CognitoActivity", "signin error : ${e?.localizedMessage}")
                }
            })

//            cognitoUser = userPool.getUser("conghua2411@yopmail.com")
//
//            cognitoUser?.getSessionInBackground(authenticationHandler)

//            cognitoUser?.getSession(authenticationHandler)
        }

        findViewById<Button>(R.id.btnGetToken).setOnClickListener {

//            if (currSession != null) {
//                Log.d("authenticationHandler", "refresh token : ${currSession?.refreshToken?.token}")
//            }

            Log.d("CognitoActivity", "user state : ${AWSMobileClient.getInstance().currentUserState().userState.name}")

            AWSMobileClient.getInstance().getTokens(object : Callback<Tokens> {
                override fun onResult(result: Tokens?) {
                    Log.d("CognitoActivity", "token : ${result?.accessToken?.tokenString}")
                }

                override fun onError(e: Exception?) {
                    Log.d("CognitoActivity", "token : ${e?.localizedMessage}")
                }
            })
        }

//        findViewById<Button>(R.id.btnSignOut).setOnClickListener {
//            cognitoUser?.signOut()
//        }
    }
}
