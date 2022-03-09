package com.example.salesrep.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.salesrep.R
import java.util.*

class StockStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_start)

        val tvskipnow : TextView = findViewById(R.id.tv_skip_for_now)
        val btn_add_stock : Button = findViewById(R.id.btn_add_stock)


        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        btn_add_stock.setOnClickListener {
            //launch the add stock activity
            val intent = Intent(this,AddStockActivity::class.java)
            startActivity(intent)
        }

        tvskipnow.setOnClickListener {
            //Launch the home screen when the user press this textview
            val intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }

    }
}