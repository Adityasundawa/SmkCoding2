package com.adityasundawa.lawancorona

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gejala_item.*

class GejalaAdapter(private val context: Context, private val items : ArrayList<GejalaCorona>) :
    RecyclerView.Adapter<GejalaAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.gejala_item, parent, false)
    )
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }
    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: GejalaCorona) {
            txtJudul.text = item.judul
            txtDetail.text = item.deskripsi


        }
    }
}