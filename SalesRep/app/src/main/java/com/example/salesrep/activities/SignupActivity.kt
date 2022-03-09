package com.example.salesrep.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.salesrep.R
import com.example.salesrep.firestore.FireStore
import com.example.salesrep.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : BaseActivity() {



    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btn_signup : Button = findViewById(R.id.btn_signup)
        val tv_login : TextView = findViewById(R.id.tv_login)

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

        tv_login.setOnClickListener {

            //Launch the login screen when the user press this textview
            /*val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)*/
            onBackPressed()
        }


        btn_signup.setOnClickListener {
            signupUser()
        }


    }

    // function to validate entries of a new user
    private fun validateSignupDetails() : Boolean
    {
        val edt_f_name : TextInputEditText = findViewById(R.id.edt_fname)
        val edt_l_name : TextInputEditText = findViewById(R.id.edt_lname)
        val edt_email : TextInputEditText = findViewById(R.id.edt_email)
        val edt_password : TextInputEditText = findViewById(R.id.edt_password)
        val edt_confirm_password : TextInputEditText = findViewById(R.id.edt_confirm_password)
        val cb_agree_terms : AppCompatCheckBox = findViewById(R.id.cb_terms_and_conditions)


        return when
        {
            TextUtils.isEmpty(edt_f_name.text.toString().trim(){ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_enter_f_name), true)
                false
            }
            TextUtils.isEmpty(edt_l_name.text.toString().trim(){ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_enter_l_name), true)
                false
            }
            TextUtils.isEmpty(edt_email.text.toString().trim(){ it <= ' '})  ->
            {
                showErrorSnackBar(resources.getString(R.string.error_enter_email), true)
                false
            }
            TextUtils.isEmpty(edt_password.text.toString().trim(){ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_enter_password), true)
                false
            }
            TextUtils.isEmpty(edt_confirm_password.text.toString().trim(){ it <= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.error_confirm_password), true)
                false
            }
            !cb_agree_terms.isChecked ->
            {
                showErrorSnackBar(resources.getString(R.string.terms_conditions),true)
                false
            }
            else ->
            {
                //showErrorSnackBar("Successfully signed up",false)
                true
            }

        }

    }


    private fun signupUser()
    {
        val edt_f_name : TextInputEditText = findViewById(R.id.edt_fname)
        val edt_l_name : TextInputEditText = findViewById(R.id.edt_lname)
        val edt_email : TextInputEditText = findViewById(R.id.edt_email)
        val edt_password : TextInputEditText = findViewById(R.id.edt_password)

        //check with the validate function
        if(validateSignupDetails())
        {
            showProgressDialog(resources.getString(R.string.please_wait))

            val email : String = edt_email.text.toString().trim() { it <= ' '}
            val password : String = edt_password.text.toString().trim { it <= ' ' }

            //creating a firebase instance and signing up the user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener{
                    task ->

                    //if successfully registered
                    if(task.isSuccessful)
                    {
                        //firebase registered user
                        val firebaseUser : FirebaseUser = task.result!!.user!!


                        val user = User(
                            firebaseUser.uid,
                            edt_f_name.text.toString().trim{ it <= ' '},
                            edt_l_name.text.toString().trim{ it <= ' '},
                            edt_email.text.toString().trim{ it <= ' '},
                        )

                        FireStore().registerUser(this@SignupActivity, user)


                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        /*FirebaseAuth.getInstance().signOut()
                        finish()*/

                    }
                    else
                    {
                        hideProgressDialog()
                        //if not registered successfully
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
            )

        }
    }

    fun userRegistrationSuccess()
    {
        //Hide the progress dialog
        hideProgressDialog()

        Toast.makeText(this, resources.getString(R.string.register_success),Toast.LENGTH_SHORT).show()

    }



}