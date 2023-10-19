package com.example.newsappv5

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.core.content.contentValuesOf

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "NewsApp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allArticles"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertArticle(article: Article){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_TITLE, article.title)
            put(COLUMN_CONTENT, article.content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllArticle(): List<Article> {
        val articleList = mutableListOf<Article>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val article = Article(id, title, content)
            articleList.add(article)

        }

        cursor.close()
        db.close()
        return articleList
    }

    fun updateArticle(article: Article){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_TITLE, article.title)
            put(COLUMN_CONTENT, article.content)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(article.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getArticleById(articleId : Int): Article {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $articleId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Article( id, title, content)
    }

    fun deleteArticle(articleId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(articleId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}
