package com.adityasundawa.lawancorona

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.profile_item.*

class ProfileAdapter(private val context: Context, private val items : ArrayList<Profile>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.profile_item, parent, false)
    )
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }
    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: Profile) {

            txtFoto.text = item.title
            txtNama.text = item.name
            txtFollowers.text = item.followers
            txtFollowing.text = item.following
            Picasso.get().load(item.image).into(img_Profile)

        }
    }
}