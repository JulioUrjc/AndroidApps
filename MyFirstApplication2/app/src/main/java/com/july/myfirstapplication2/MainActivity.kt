package com.july.myfirstapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(8000)
        setTheme(R.style.Theme_MyFirstApplication2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}