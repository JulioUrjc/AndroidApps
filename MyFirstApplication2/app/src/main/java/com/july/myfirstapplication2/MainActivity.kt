package com.july.myfirstapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_MyFirstApplication2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //textView.text = "Cambiar el texto"
        //textView.text = getString(R.string.Hello)

    }
}