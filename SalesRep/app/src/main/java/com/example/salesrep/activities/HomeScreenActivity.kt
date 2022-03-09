package com.example.salesrep.activities

import android.content.ClipData
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.salesrep.R
import com.example.salesrep.models.User
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.internal.ToolbarUtils

class HomeScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val user_icon : View = findViewById(R.id.user)
        val settings_icon : View = findViewById(R.id.settings)
        val ll_stock : LinearLayout = findViewById(R.id.ll_stock)
        val ll_shop : LinearLayout = findViewById(R.id.ll_shop)
        val ll_route : LinearLayout = findViewById(R.id.ll_route)
        val ll_history : LinearLayout = findViewById(R.id.ll_history)


        //when user clicks the user icon
        user_icon.setOnClickListener {
            //To see the user profile, launch the settings activity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        //when user clicks the settings icon
        settings_icon.setOnClickListener {
            //To see the user profile, launch the settings activity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the stocks function
        ll_stock.setOnClickListener {
            //To see the stock, launch  activity
            val intent = Intent(this, StockActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the shops function
        ll_shop.setOnClickListener {
            //To see the available shops, launch  activity
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the routes function
        ll_route.setOnClickListener {
            //To see the available shops, launch  activity
            val intent = Intent(this, RouteActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the history function
        ll_history.setOnClickListener {
            //To see the available shops, launch  activity
            val intent = Intent(this, ViewHistoryActivity::class.java)
            startActivity(intent)
        }


    }
}