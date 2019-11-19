package com.example.leclevietnam.demoeverything

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.AppComponent
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.DaggerAppComponent
import com.example.leclevietnam.demoeverything.koinDemo.appModule
import com.example.leclevietnam.demoeverything.retrofit.module2
import com.google.firebase.FirebaseApp
import com.kakao.auth.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DemoApplication : Application() {

    var appComponent: AppComponent? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        KakaoSDK.init(object: KakaoAdapter() {
            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig { this@DemoApplication }
            }

            override fun getSessionConfig(): ISessionConfig {
                return object: ISessionConfig {
                    override fun isSaveFormData(): Boolean {
                        return true
                    }

                    override fun getAuthTypes(): Array<AuthType> {
                        return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                    }

                    override fun isSecureMode(): Boolean {
                        return false
                    }

                    override fun getApprovalType(): ApprovalType? {
                        return ApprovalType.INDIVIDUAL
                    }

                    override fun isUsingWebviewTimer(): Boolean {
                        return false
                    }
                }
            }
        })

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        startKoin {
            androidLogger()
            androidContext(this@DemoApplication)
            modules(appModule, module2)
        }

//        startForegroundService(Intent(this, InternetService::class.java))
    }
}
