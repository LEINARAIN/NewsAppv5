package com.example.newsappv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton:Button =findViewById(R.id.loginButton)
        val username:EditText =findViewById(R.id.username)
        val password:EditText =findViewById(R.id.password)
        val nextButton:Button =findViewById(R.id.nextButton)

        loginButton.setOnClickListener {
            val usernameInput = username.text.toString()
            val passwordInput = password.text.toString()

            if (usernameInput.equals("admin") && passwordInput.equals("password123")){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
            }else if(usernameInput.equals("") && passwordInput.equals("")) {
                Toast.makeText(this,"Please Enter Valid Input", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login Unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
        nextButton.setOnClickListener {
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
        }

    }
}