package com.adityasundawa.lawancorona

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityasundawa.lawancorona.viewmodel.KonsultasiFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_konsultasi.*

class KonsultasiFragment : Fragment(), KonsultasiAdapter.dataListener {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    var dataKonsul: MutableList<Konsultasi> = ArrayList()
    private val viewModel by viewModels<KonsultasiFragmentViewModel>()
    private var adapter: KonsultasiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_konsultasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
        viewModel.init(requireContext());
        viewModel.allMyFriends.observe(viewLifecycleOwner, Observer{ myFriends ->
            myFriends?.let { adapter?.setData(it) }
        })
        fab.setOnClickListener {
            val intent = Intent (getActivity(), KonsulActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun init(){
        rv_Konsultasi.layoutManager = LinearLayoutManager(context)
        adapter = KonsultasiAdapter(requireContext(), dataKonsul)
        rv_Konsultasi.adapter = adapter
        adapter?.listener = this
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Konsultasi").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataKonsul = ArrayList()
                for (snapshot in dataSnapshot.children) {
                    val konsul = snapshot.getValue(Konsultasi::class.java)
                    konsul?.key = (snapshot.key!!)
                    dataKonsul.add(konsul!!)
                }
                viewModel.insertAll(dataKonsul)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    override fun onDeleteData(data: Konsultasi, position: Int) {
        /*
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter pada RecyclerView melalui interface.
         * kemudian akan menghapus data berdasarkan primary key dari data tersebut
         * Jika berhasil, maka akan memunculkan Toast
         */
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        if (ref != null) {
            ref.child(getUserID)
                .child("Konsultasi")
                .child(data?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(context, data!!.key!!.toString(), Toast.LENGTH_LONG).show()
        }
    }
}