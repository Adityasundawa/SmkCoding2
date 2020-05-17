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
import kotlinx.android.synthetic.main.gejala_item.*
import kotlinx.android.synthetic.main.gejala_item.view.*
import kotlinx.android.synthetic.main.news_item.*

class NewsAdapter(private val context: Context, private val items : ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
    )
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }
    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: News) {
            newsTitle.text = item.title
            newsInfo.text = item.info
            Picasso.get().load(item.image).into(thumbnail)






        }
    }
}