package com.adityasundawa.lawancorona

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_konsultasi.*

class KonsultasiFragment : Fragment(), KonsultasiAdapter.dataListener {
    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTeman: ArrayList<Konsultasi>
    lateinit var listTeman: ArrayList<Konsul>
    private fun simulasiDataTeman() { //ini sudah tidak digunakan lagi
        listTeman = ArrayList()
        listTeman.add(
            Konsul(
                "Aditya", "laki-laki", "86755677",
                "081123123123"
            )
        )
        listTeman.add(
            Konsul(
                "Sundawa", "laki-laki", "8678567567",
                "085123123123"
            )
        )
    }

    private fun tampilTeman() { //ini sudah tidak digunakan lagi
        rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
        //rv_listMyFriends.adapter = MyFriendAdapter(activity!!, listTeman)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_konsultasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        fab.setOnClickListener {
            val intent = Intent(getActivity(), KonsulActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(
            getContext(), "Mohon Tunggu Sebentar...",
            Toast.LENGTH_LONG
        ).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(
                    getContext(), "Database Error yaa...",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Inisialisasi ArrayList
                dataTeman = java.util.ArrayList<Konsultasi>()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val teman = snapshot.getValue(Konsultasi::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    teman?.key = (snapshot.key!!)
                    dataTeman.add(teman!!)
                }
                //Memasang Adapter pada RecyclerView
                rv_listMyFriends.layoutManager = LinearLayoutManager(context)
                rv_listMyFriends.adapter = KonsultasiAdapter(context!!, dataTeman)
                Toast.makeText(
                    getContext(), "Data Berhasil Dimuat",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    override fun onDeleteData(data: Konsultasi, position: Int) {
        TODO("Not yet implemented")
    }
}
