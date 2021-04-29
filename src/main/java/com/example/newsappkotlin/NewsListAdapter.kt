package com.example.newsappkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsListHolder>() {

    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)
        val viewHolder = NewsListHolder(view)
        view.setOnClickListener{
           listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsListHolder, position: Int) {
       val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun updateNews(updatedItems: ArrayList<News>){
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()

    }

}

class NewsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.titleTV)
    val author: TextView = itemView.findViewById(R.id.author)
    val imageView: ImageView = itemView.findViewById(R.id.newsImage)
}
interface NewsItemClicked {
    fun onItemClicked(item: News)
}