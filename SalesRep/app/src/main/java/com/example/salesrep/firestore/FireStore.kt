package com.example.salesrep.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.salesrep.activities.LoginActivity
import com.example.salesrep.activities.SettingsActivity
import com.example.salesrep.activities.SignupActivity
import com.example.salesrep.models.User
import com.example.salesrep.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStore {

    private val firestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignupActivity, userInfo: User)
    {
        //"users" is the collection name. It will be created if it is not yet created
        firestore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                //calling a function of the base activity  to transfer the results to it
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener {
                        e ->
                    activity.hideProgressDialog()
                    Log.e(
                        activity.javaClass.simpleName,
                        "Error occurred",
                        e
                    )
            }

    }

    fun getCurrentUserId() : String
    {
        //An instance of current user using firebase auth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to store the current userid
        var currentUserID = ""
        if(currentUser != null)
        {
            currentUserID = currentUser.uid
        }
        return currentUserID

    }

    fun getUserDetails(activity : Activity)
    {
        //we pass the collection name from which we want the data
        firestore.collection(Constants.USERS)
            //document id to get the fields of user
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener {
                document ->
                Log.i(activity.javaClass.simpleName, document.toString())


                //here we get the document snapshot which is converted into the user data model object
                val user = document.toObject(User::class.java)!!


                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.SALESREP_PREFERENCES,
                        Context.MODE_PRIVATE
                    )
                val editor : SharedPreferences.Editor = sharedPreferences.edit()
                //key : value   logged in username : John Doe
                editor.putString(
                    Constants.LOGGED_IN_USERNAME,
                    "${user.firstName} ${user.lastName}"
                )
                editor.apply()

                when(activity)
                {
                    is LoginActivity ->
                        // call a function of the base activity for transferring the result to it
                    {
                        activity.userLoggedInSuccess(user)
                    }

                    is SettingsActivity ->
                    {
                        activity.userDetailsSuccess(user)
                    }
                }


            }
    }



}