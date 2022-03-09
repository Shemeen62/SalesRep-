package com.example.salesrep.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import com.example.salesrep.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val tv_app_name : TextView = findViewById(R.id.tv_app_name)

        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            //hide status bar
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            window.setFlags(

                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                startActivity(Intent(this, LoginActivity::class.java))
            },
            1500
        )

        val typeface : Typeface = Typeface.createFromAsset(assets, "Poppins-Medium.ttf")
        tv_app_name.typeface = typeface


    }
}