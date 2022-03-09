package com.example.salesrep.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.salesrep.R
import com.example.salesrep.models.Shop
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class AddShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shop)

        val btn_add : Button = findViewById(R.id.btn_add_shop)
        val btn_cancel : Button = findViewById(R.id.btn_cancel)

        var database = FirebaseDatabase.getInstance().reference

        btn_add.setOnClickListener {

            val shopName : TextInputEditText = findViewById(R.id.edt_shop_name)
            val shopOwner : TextInputEditText = findViewById(R.id.edt_owner)
            val address : TextInputEditText = findViewById(R.id.edt_address)
            val contact : TextInputEditText = findViewById(R.id.edt_contact)
            val route : TextInputEditText = findViewById(R.id.edt_route)

            var shop = shopName.text.toString().trim()
            var owner = shopOwner.text.toString().trim()
            var shop_address = address.text.toString().trim()
            var contact_no = contact.text.toString().trim()
            var routes = route.text.toString().trim()

            database.child("Shop").child(shop).setValue(Shop(owner, shop_address, contact_no, routes))

            Toast.makeText(this, "Record added successfully!", Toast.LENGTH_LONG).show()
        }

        btn_cancel.setOnClickListener {
            onBackPressed()
        }

    }
}