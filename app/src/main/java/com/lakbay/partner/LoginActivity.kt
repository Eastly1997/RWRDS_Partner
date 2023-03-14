package com.lakbay.partner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.lakbay.partner.databinding.ActivityLoginBinding
import com.lakbay.partner.utils.FirebaseUtils
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
        MobileAds.initialize(this)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null) {
            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
                    .putExtra(Restaurant.FIELD_UID, auth.currentUser!!.uid)
            )
            finishAffinity()
        }

        binding.loginClick = View.OnClickListener {

            Log.e("Login", "email: " + binding.email + " password: " + binding.password)
            auth.signInWithEmailAndPassword(binding.email!!.trim(), binding.password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(FirebaseUtils.TAG, "signInWithEmail:success")
                        startActivity(
                            Intent(this@LoginActivity, MainActivity::class.java)
                                .putExtra(Restaurant.FIELD_UID, auth.currentUser!!.uid)
                        )
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
}