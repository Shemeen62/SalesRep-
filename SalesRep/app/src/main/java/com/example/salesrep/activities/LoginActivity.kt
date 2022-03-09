package com.example.salesrep.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.salesrep.R
import com.example.salesrep.firestore.FireStore
import com.example.salesrep.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class LoginActivity : BaseActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tv_signup : TextView = findViewById(R.id.tv_signup)
        val btn_login : Button = findViewById(R.id.btn_login)



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

        btn_login.setOnClickListener(this)
        tv_signup.setOnClickListener(this)

    }

    fun userLoggedInSuccess(user: User)
    {
        //hide the progress dialog
        hideProgressDialog()

        //print the user details in the log as of now
        Log.i("First Name", user.firstName)
        Log.i("Last Name", user.lastName)
        Log.i("Email", user.email)

        //code to run the start stock activity once a day (At the time of first login each day)
        val cal : Calendar = Calendar.getInstance()
        val today = cal.get(Calendar.DAY_OF_MONTH)
        val settings : SharedPreferences = getSharedPreferences("PREFS",0)
        val lastDay = settings.getInt("day",0)

        if (lastDay != today)
        {
            val editor : SharedPreferences.Editor = settings.edit()
            editor.putInt("day", today)
            editor.apply()
        }

        //Redirect the user to home screen after login
        startActivity(Intent(this@LoginActivity, StockStartActivity::class.java))
        finish()

    }




    override fun onClick(view: View?)
    {
        if(view != null)
        {
            when(view.id)
            {
                //if user clicks the login button
                R.id.btn_login ->
                {
                    loginRegisteredUser()
                }
                //if user clicks the signup textview
                R.id.tv_signup ->
                {
                    val intent = Intent(this,SignupActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


    //function to validate login details
    private fun validateLogin() : Boolean
    {
        val edt_email : TextInputEditText = findViewById(R.id.edt_email)
        val edt_pass : TextInputEditText = findViewById(R.id.edt_pass)


        return when
        {
            TextUtils.isEmpty(edt_email.text.toString().trim{ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_email), true)
                false
            }
            TextUtils.isEmpty(edt_pass.text.toString().trim{ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_password), true)
                false
            }
            else ->
            {
                showErrorSnackBar("Your details are valid",false)
                true
            }
        }
    }


    //function to validate and login registered users
    private fun loginRegisteredUser()
    {
        val edt_email : TextInputEditText = findViewById(R.id.edt_email)
        val edt_pass : TextInputEditText = findViewById(R.id.edt_pass)

        if(validateLogin())
        {
            //show the progress dialog message
            showProgressDialog(resources.getString(R.string.please_wait))

            //get the user entered details from the edit text and trim it
            val email = edt_email.text.toString().trim{ it <= ' '}
            val password = edt_pass.text.toString().trim{ it <= ' '}

            //Login using firebase authentication
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->

                if(task.isSuccessful)
                {
                    FireStore().getUserDetails(this@LoginActivity, )
                }
                else
                {
                    hideProgressDialog()
                    showErrorSnackBar(task.exception!!.message.toString(), true)
                }
            }

        }
    }













}