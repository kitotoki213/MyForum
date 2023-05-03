package com.example.myforum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.myforum.MainActivity
import com.example.myforum.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //用协程延迟2秒以展示Splash页面，再跳转到MainActivity页面
        lifecycleScope.launch {
            delay(2000)
            Intent(this@SplashActivity, MainActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}