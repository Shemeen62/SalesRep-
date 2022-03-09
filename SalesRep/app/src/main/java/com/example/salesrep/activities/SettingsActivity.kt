package com.example.salesrep.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.salesrep.R
import com.example.salesrep.firestore.FireStore
import com.example.salesrep.models.User
import com.example.salesrep.utils.SRPButton
import com.example.salesrep.utils.SRPTextView
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : BaseActivity(), View.OnClickListener {

    //val toolbar_settings_activity : Toolbar = findViewById(R.id.toolbar_settings_activity)
    /*val tv_name : TextView = findViewById(R.id.tv_name)
    val tv_email : TextView = findViewById(R.id.tv_email)*/
    private lateinit var mUserDetails : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btn_logout : Button = findViewById(R.id.btn_logout)

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }

        //setupActionBar()
        //btn_logout.setOnClickListener(this)

    }


    //setting up the actionbar
    /*private fun setupActionBar()
    {
        setSupportActionBar(toolbar_settings_activity)

        val actionBar = supportActionBar
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back)
        }

        toolbar_settings_activity.setNavigationOnClickListener{ onBackPressed() }

     }
    */


    //function to get user details from fire store
    private fun getUserDetails()
    {
        showProgressDialog(resources.getString(R.string.please_wait))
        FireStore().getUserDetails(this)
    }


    fun userDetailsSuccess(user : User)
    {
        mUserDetails = user

        hideProgressDialog()

        /*tv_name.text = "${user.firstName} ${user.lastName}"
        tv_email.text = "${user.email}"*/

    }



    override fun onResume() {
        super.onResume()
        getUserDetails()
    }

    override fun onClick(view: View?) {

        if(view != null)
        {
            when(view.id)
            {
                R.id.btn_logout ->
                {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

    }


}