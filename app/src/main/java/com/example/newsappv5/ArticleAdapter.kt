package com.example.newsappv5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private var article: List<Article>, context: Context) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val db:DatabaseHelper = DatabaseHelper(context)

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView : TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.editButton)
        val deleteButton : ImageView = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = article.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = article[position]
        holder.titleTextView.text = article.title
        holder.contentTextView.text = article.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateArticleActivity::class.java).apply {
                putExtra("article_id", article.id)
            }

            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteArticle(article.id)
            refreshData(db.getAllArticle())
            Toast.makeText(holder.itemView.context, "Article Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData (newArticle : List<Article>){
        article = newArticle
        notifyDataSetChanged()
    }

}
