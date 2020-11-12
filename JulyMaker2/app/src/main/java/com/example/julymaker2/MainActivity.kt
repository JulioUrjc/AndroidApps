package com.example.julymaker2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Enum clases
        //enumClasses()

        // Nested and Inner Classes
        //nestedAndInnerClasses()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Leccion 1
    enum class Direction(val dir:Int){
        NORTH(1), SOUTH(-1), EAST(1), WEST(-1);

        fun description() : String{
            return when(this){
                NORTH ->{"La direccion es Norte"}
                SOUTH ->{"La direccion es Sur"}
                EAST ->{"La direccion es Este"}
                WEST ->{"La direccion es Oeste"}
            }
        }
    }

    // Leccion 1: enum class
    private fun enumClasses()
    {
        var userDirection: Direction? = null
        println("Direccion: $userDirection")

        userDirection = Direction.EAST

        userDirection.name    // EAST en formato String
        userDirection.ordinal // Posicion del enumerado
        userDirection.description()
        userDirection.dir
    }

    // Leccion 2: Nested and Inner Classes
    private fun nestedAndInnerClasses()
    {
        //Clases anidadas (Nested Class)
        val MyNestedClass = MyNestedAndInnerClass.MyNestedClass()
        val sum = MyNestedClass.sum(10,5)

        //Clases internas (Inner Class)
        val myInnerClass = MyNestedAndInnerClass().MyInnerClass()
        val sumTwo = myInnerClass.sumTwo(10)
    }

}