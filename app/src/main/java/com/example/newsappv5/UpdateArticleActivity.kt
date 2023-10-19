package com.example.newsappv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsappv5.databinding.ActivityUpdateArticleBinding

class UpdateArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateArticleBinding
    private lateinit var db:DatabaseHelper
    private var articleId : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        articleId = intent.getIntExtra("article_id", -1)
        if (articleId == -1){
            finish()
            return
        }

        val article = db.getArticleById(articleId)
        binding.updateTitleEditText.setText(article.title)
        binding.updateContentEditText.setText(article.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedArticle = Article(articleId, newTitle, newContent)

            db.updateArticle(updatedArticle)
            finish()
            Toast.makeText(this,"Article Saved", Toast.LENGTH_SHORT).show()
        }
    }
}