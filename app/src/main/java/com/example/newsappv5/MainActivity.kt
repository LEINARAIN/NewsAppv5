package com.example.newsappv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsappv5.databinding.ActivityMainBinding
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:DatabaseHelper
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        articleAdapter = ArticleAdapter(db.getAllArticle(), this)

        binding.articleRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.articleRecyclerView.adapter = articleAdapter

        binding.createButtton.setOnClickListener{
            val intent = Intent(this, CreateArticleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        articleAdapter.refreshData(db.getAllArticle())
    }
}