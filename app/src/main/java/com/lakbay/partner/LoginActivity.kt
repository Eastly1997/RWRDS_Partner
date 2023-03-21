package com.lakbay.partner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lakbay.partner.databinding.ActivityLoginBinding
import com.lakbay.partner.utils.CommonConstants
import com.lakbay.partner.utils.FirebaseUtils
import com.lakbay.partner.utils.SharedPrefUtils
import com.lakbay.partner.viewmodel.Restaurant

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val auth = FirebaseAuth.getInstance()
        initData()

        if(auth.currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finishAffinity()
        }

        binding.loginClick = View.OnClickListener {

            Log.e("Login", "email: " + binding.email + " password: " + binding.password)
            auth.signInWithEmailAndPassword(binding.email!!.trim(), binding.password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        SharedPrefUtils.saveData(this@LoginActivity, Restaurant.FIELD_UID, auth.currentUser!!.uid);
                        Log.d(FirebaseUtils.TAG, "signInWithEmail:success")
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finishAffinity()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(FirebaseUtils.TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        binding.password = "";
                    }
                }
        }
    }

    private fun initData() {
        FirebaseApp.initializeApp( this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()

        val storageEnv = SharedPrefUtils.getStringData(this, CommonConstants.ENVIRONMENT)
        val currentEnv = CommonConstants.ENVIRONMENT_STAGING

        if(storageEnv == CommonConstants.ENVIRONMENT_STAGING) {
            firebaseAppCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance()
            )
        }

        if(storageEnv != currentEnv) {
            Firebase.auth.signOut();
            SharedPrefUtils.clearData(this)
            SharedPrefUtils.saveData(this, CommonConstants.ENVIRONMENT, currentEnv)
        }

    }
}