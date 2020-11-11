package com.example.julymaker

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

        // Leccion 1
        //variablesYConstantes()

        // Leccion 2
        //tiposDeDatos()

        // Leccion 3
        //sentenciaIf()

        // Leccion 4
        //sentenciaWhen()

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

    /* Definicion de constantes y variables*/
    private fun variablesYConstantes()
    {
        //Constantes
        val myFirstConst = "Mi constante"

        //Variables
        var myVariable = "Hello JulyMaker"
        var mySecondVariable = myVariable
        var myNumber = 1

        println(myVariable)
        myVariable = "Hola Mundo julyMaker"
        println(myVariable)
        println(mySecondVariable)
    }

    /* Tipos de datos */
    private fun tiposDeDatos()
    {
        // String
        val myString = "Hola julyMaker"
        val myString2: String = " que chulo!"

        val myString3 = myString+" "+myString2
        //println(myString3)

        // Enteros (Byte, Shoert, Int, Long)
        var myInt: Int = 1
        var myInt2 = 2
        var myInt3 = myInt + myInt2

        //println(myInt3)

        // Decimales (Float, Double)
        var myDouble: Double = 2.5
        var myDouble2 = 3.5f
        var myDouble3 = myDouble +myDouble2 + myInt3

        // Boolean (Bool)
        var myBool = true
        var myBool2 = false

        //println(myBool == myBool2)
        //println(myBool && myBool2)
    }

    /* Sentencia If */
    private fun sentenciaIf()
    {
        val myNumber = 10

        // Operaciones condicionales
        // >, >, >=, <=, ==, !=
        //Operadores lógicos
        // &&, ||, !

        if(myNumber < 10 && myNumber > 5)
        {
            println("$myNumber es menor que 10")
        } else {
            println("$myNumber es mayor que 10")
        }

        if(myNumber in 6..9)
        {
            println("$myNumber es menor que 10")
        } else if(myNumber == 60){
            println("$myNumber es 60")
        }else {
            println("$myNumber es mayor que 10")
        }
    }

    /* Sentencia When/ Switch */
    private fun sentenciaWhen()
    {
        val country = "España"

        when (country){
            "España" ->{

            }
        }
    }
}