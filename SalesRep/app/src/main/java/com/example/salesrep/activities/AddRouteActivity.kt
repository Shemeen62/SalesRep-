package com.example.salesrep.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.salesrep.R
import com.example.salesrep.models.Route
import com.example.salesrep.models.Shop
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class AddRouteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route)

        val btn_add : Button = findViewById(R.id.btn_add_route)
        val btn_cancel : Button = findViewById(R.id.btn_cancel)

        val database = FirebaseDatabase.getInstance().reference

        btn_add.setOnClickListener {

            val number : TextInputEditText = findViewById(R.id.edt_number)
            val start : TextInputEditText = findViewById(R.id.edt_start)
            val end : TextInputEditText = findViewById(R.id.edt_end)
            val distance : TextInputEditText = findViewById(R.id.edt_distance)

            val rtNo = number.text.toString().trim()
            val rtStart = start.text.toString().trim()
            val rtEnd = end.text.toString().trim()
            val rtDistance = distance.text.toString().trim()


            database.child("Route").child(rtNo).setValue(Route(rtStart, rtEnd, rtDistance))

            Toast.makeText(this, "Record added successfully!", Toast.LENGTH_LONG).show()
        }

        btn_cancel.setOnClickListener {
            onBackPressed()
        }

    }
}