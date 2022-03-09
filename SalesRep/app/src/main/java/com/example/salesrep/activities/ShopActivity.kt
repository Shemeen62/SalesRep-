package com.example.salesrep.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.salesrep.R

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val btn_add_shop : Button = findViewById(R.id.btn_add_shop)
        val btn_view_shop : Button = findViewById(R.id.btn_view_shop)

        //when user clicks the add shop button
        btn_add_shop.setOnClickListener {
            val intent = Intent(this, AddShopActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the view shop button
        btn_view_shop.setOnClickListener {
            val intent = Intent(this, ViewShopsActivity::class.java)
            startActivity(intent)
        }


    }
}