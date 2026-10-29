package ir.abrova.chat.example.ecommerce

import android.app.Application
import android.util.Log
import ir.abrova.push.sdk.AbrovaPush
import ir.abrova.push.sdk.AbrovaPushConfig
import ir.abrova.push.sdk.AbrovaPushLogLevel

class EcommerceApplication : Application() {

    companion object {
        private const val TAG = "EcommerceApp"
        // Same public key as the AbrovaChat SDK, from local.properties (ABROVA_API_KEY).
        private val API_KEY = BuildConfig.ABROVA_API_KEY
    }

    override fun onCreate() {
        super.onCreate()

        if (API_KEY.isBlank()) {
            Log.e(TAG, "ABROVA_API_KEY is not set: copy local.properties.example to local.properties and add your key")
            return
        }

        val config = AbrovaPushConfig(
            apiKey = API_KEY,
            showServiceNotification = false
        )

        AbrovaPush.init(this, config)
        AbrovaPush.setLogLevel(AbrovaPushLogLevel.DEBUG)

        Log.i(TAG, "Abrova Push SDK initialized")
    }
}
