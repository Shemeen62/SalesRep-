package com.example.salesrep.activities

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.salesrep.R
import com.example.salesrep.models.User
import com.example.salesrep.utils.Constants
import com.google.android.material.textfield.TextInputEditText
import java.util.jar.Manifest

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


    }

}