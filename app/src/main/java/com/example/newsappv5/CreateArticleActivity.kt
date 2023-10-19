package com.example.newsappv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsappv5.databinding.ActivityCreateArticleBinding
import com.example.newsappv5.databinding.ActivityMainBinding

class CreateArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateArticleBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_article)

        binding = ActivityCreateArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val article = Article(0, title, content)
            db.insertArticle(article)
            finish()
            Toast.makeText(this, "Article Posted", Toast.LENGTH_SHORT).show()
        }
    }
}