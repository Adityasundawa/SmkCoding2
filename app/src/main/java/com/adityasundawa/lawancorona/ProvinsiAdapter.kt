package com.adityasundawa.lawancorona

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.province_item.*

class ProvinsiAdapter(private val context :Context,private val items : List<DataProvinsiItem>,
                      private val listener:(DataProvinsiItem)->Unit):RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.province_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: DataProvinsiItem, listener: (DataProvinsiItem) -> Unit) {
            txtPositif.text = item.attributes.kasusPosi.toString()
            txtMeninggal.text = item.attributes.kasusMeni.toString()
            txtSembuh.text = item.attributes.kasusSemb.toString()
            txtProvince.text = item.attributes.provinsi.toString()
            containerView.setOnClickListener { listener(item) }
        }
    }

}