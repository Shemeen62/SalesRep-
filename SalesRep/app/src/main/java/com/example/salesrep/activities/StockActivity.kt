package com.example.salesrep.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.salesrep.R

class StockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        val btn_add_stock : Button = findViewById(R.id.btn_add_stock)
        val btn_view_stock : Button = findViewById(R.id.btn_view_stock)

        //when user clicks the add stocks button
        btn_add_stock.setOnClickListener {
            val intent = Intent(this, AddStockActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the view stocks button
        btn_view_stock.setOnClickListener {
            val intent = Intent(this, ViewStockActivity::class.java)
            startActivity(intent)
        }
    }
}