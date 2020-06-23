package com.adityasundawa.lawancorona

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        progress.visibility = View.GONE
        login.setOnClickListener(this)
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser == null) {
        } else {
            intent = Intent(applicationContext,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            //Jika Berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil",
                    Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext,
                    MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { //Jika gagal login
                progress.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onClick(v: View?) {
        // Statement program untuk login/masuk
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
        progress.visibility = View.VISIBLE
    }
}