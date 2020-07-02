package com.adityasundawa.lawancorona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.adityasundawa.lawancorona.viewmodel.KonsultasiViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_konsul.*
import kotlinx.android.synthetic.main.activity_my_update.*

class KonsulActivity : AppCompatActivity() {
    private var Nama: EditText? = null
    private var Email: EditText? = null
    private var Telp: EditText? = null
    private var Pesan: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null
    private val viewModel by viewModels<KonsultasiViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsul)
        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById<EditText>(R.id.email)
        Telp = findViewById<EditText>(R.id.telp)
        Pesan = findViewById<EditText>(R.id.pesan)
        viewModel.init(this)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Autentifikasi
        simpan.setOnClickListener{
            prosesSave()
        }

    }
    private fun prosesSave() {
        val getNama: String = Nama?.getText().toString()
        val getEmail: String = Email?.getText().toString()
        val getTelp: String = Telp?.getText().toString()
        val getAlamat: String = Pesan?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() &&
            getAlamat.isEmpty()) {
            Toast.makeText(this@KonsulActivity,"Data tidak boleh ada yang kosong",
                Toast.LENGTH_SHORT).show()
        } else {
            val teman = Konsultasi(getNama, getEmail, getTelp, getAlamat, "")
            ref.child(getUserID).child("Konsultasi").push().setValue(teman).addOnCompleteListener {
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                Nama!!.setText("")
                Email!!.setText("")
                Telp!!.setText("")
                Pesan!!.setText("")
                teman.key = ref.key.toString()
                viewModel.addData(teman)
            }
            finish()
        }
    }
}

