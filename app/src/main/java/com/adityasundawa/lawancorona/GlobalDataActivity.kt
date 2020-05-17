package com.adityasundawa.lawancorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adityasundawa.lawancorona.data.CoronaGlobalService
import kotlinx.android.synthetic.main.activity_global_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_data)
    }
}