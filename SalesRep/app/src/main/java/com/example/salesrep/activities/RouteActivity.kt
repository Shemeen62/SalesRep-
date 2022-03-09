package com.example.salesrep.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.salesrep.R

class RouteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        val btnAddRoute : Button = findViewById(R.id.btn_add_route)
        val btnViewRoute : Button = findViewById(R.id.btn_view_routes)


        //when user clicks the add route button
        btnAddRoute.setOnClickListener {
            //To see the user profile, launch the settings activity
            val intent = Intent(this, AddRouteActivity::class.java)
            startActivity(intent)
        }

        //when user clicks the view routes button
        btnViewRoute.setOnClickListener {
            //To see the user profile, launch the settings activity
            val intent = Intent(this, ViewRoutesActivity::class.java)
            startActivity(intent)
        }

    }
}