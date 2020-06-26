package com.adityasundawa.lawancorona

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.konsultasi_item.*


class KonsultasiAdapter(
    private val context: Context,
    private var list: List<Konsultasi>
)
    : RecyclerView.Adapter<KonsultasiAdapter.ViewHolder> () {
    var listener: dataListener? = null
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.konsultasi_item, parent, false)
    )

    fun setData(list: List<Konsultasi>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))
        val Nama: String? = list.get(position).nama
        val Email: String? = list.get(position).email
        val Telp: String? = list.get(position).telp
        val Pesan: String? = list.get(position).pesan
        holder.txtNama.setText("Nama: $Nama")
        holder.txtFEmail.setText("Email: $Email")
        holder.txtTelp.setText("Telp: $Telp")
        holder.txtPesan.setText("Pesan: $Pesan")

        holder.ListItem?.setOnLongClickListener(OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("dataNama", list.get(position).nama)
                        bundle.putString("dataEmail", list.get(position).email)
                        bundle.putString("dataTelp", list.get(position).telp)
                        bundle.putString("dataAlamat", list.get(position).pesan)
                        bundle.putString("getPrimaryKey", list.get(position).key)
                        val intent = Intent(view.context, MyUpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        listener?.onDeleteData(list.get(position), position)
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Teman")
                                .child(list.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, list.get(position)?.key.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            alert.create()
            alert.show()
            true
        })
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        var ListItem: LinearLayout? = null
        fun bindItem(item: Konsultasi) {
            txtNama.text = item.nama
            txtFEmail.text = item.email
            txtTelp.text = item.telp
            txtPesan.text = item.pesan
            ListItem = itemView.findViewById<LinearLayout>(R.id.list_item)

        }
    }

    //Membuat Interfece
    interface dataListener {
        fun onDeleteData(data: Konsultasi, position: Int)
    }
}