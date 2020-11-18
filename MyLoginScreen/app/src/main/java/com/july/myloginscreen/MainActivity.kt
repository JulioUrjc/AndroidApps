package com.july.myloginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logButton.setOnClickListener{
            showHome()
        }
    }

    private fun showHome(){
        val homeIntent: Intent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }
}