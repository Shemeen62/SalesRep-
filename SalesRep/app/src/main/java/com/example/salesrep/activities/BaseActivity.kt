package com.example.salesrep.activities

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.salesrep.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    private lateinit var progressDialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    @SuppressLint("ShowToast")
    fun showErrorSnackBar(message: String, errorMessage: Boolean)
    {
        val snackBar =  Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if(errorMessage)
        {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.snackBarError
                )
            )
        }
        else
        {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.snackBarSuccess
                )
            )
        }
        snackBar.show()
    }


    //progress dialog function
    fun showProgressDialog(string: String)
    {
        progressDialog = Dialog(this)

        /* Set the screen content from a layout resource. The resource will be inflated by adding all the top level views to the screen*/
        progressDialog.setContentView(R.layout.dialog_progress)

        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)

        //start displaying the dialog
        progressDialog.show()

    }


    fun hideProgressDialog()
    {
        progressDialog.dismiss()
    }

    fun doubleBackToExit()
    {
        if(doubleBackToExitPressedOnce)
        {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true

        Toast.makeText(
            this,
            "Please click back again to exit",
            Toast.LENGTH_SHORT
        ).show()

        @Suppress ("DEPRECATION")
        Handler().postDelayed({doubleBackToExitPressedOnce = false}, 2000)

    }

}