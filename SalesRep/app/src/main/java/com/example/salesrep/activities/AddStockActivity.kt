package com.example.salesrep.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.salesrep.R
import com.example.salesrep.models.Stock
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class AddStockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stock)

        val btnAddStock : Button = findViewById(R.id.btn_add_stock)
        val btn_cancel : Button = findViewById(R.id.btn_cancel)

        var database = FirebaseDatabase.getInstance().reference

        btnAddStock.setOnClickListener {

            val itemName : TextInputEditText = findViewById(R.id.edt_item_name)
            val quantity : TextInputEditText = findViewById(R.id.edt_quantity)
            val unitPrice : TextInputEditText = findViewById(R.id.edt_unit_price)

            var item = itemName.text.toString().trim()
            var quantities = quantity.text.toString().trim()
            var unitPrices = unitPrice.text.toString().trim()

            database.child("Stock").child(item).setValue(Stock(quantities, unitPrices))

            Toast.makeText(this, "Record added successfully!", Toast.LENGTH_LONG).show()

        }

        btn_cancel.setOnClickListener {
            onBackPressed()
        }


    }


}
