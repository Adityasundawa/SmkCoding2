package com.adityasundawa.lawancorona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_konsultasi.*

class KonsultasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsultasi)
        fab.setOnClickListener {
            val intent = Intent(this, KonsulActivity::class.java)
            startActivity(intent)
        }

    }
}