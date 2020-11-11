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

        // Leccion 5
        //arrays()

        // Leccion 6
        //diccionarios()

        // Leccion 7
        //bucles()

        // Leccion 8
        //nullSafety()

        // Leccion 9
        //funciones()

        // Leccion 10
        clases()

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

        // Enteros (Byte, Short, Int, Long)
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
            println("${myNumber} es menor que 10")
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
            "España", "Mexico" ->{
                println("El idioma es Español")
            } "EEUU" ->{
                println("El idioma es Inglés")
            } else ->{
                println("No conocemos el idioma")
            }
        }

        println("")
    }

    /* Tipo array */
    fun arrays()
    {
        val name = "July"
        val surname = "Maker"

        // Ordenado y repetido
        val myArray = arrayListOf<String>()
        myArray.add(name)
        myArray.add(surname)
        println(myArray)

        myArray.addAll(listOf("Hola"," bienvenidos a esta app"))

        val myName = myArray[0]
        myArray[1] = "maker"
        myArray.removeAt(3)

        myArray.forEach{
            println(it)
        }

        myArray.last()
        myArray.sort()
        myArray.count()
        myArray.clear()
    }

    /* Tipo diccionario */
    fun diccionarios()
    {
        var myMap: Map<String,Int> = mapOf() //Vacio {}

        // No existe orden y 1 sola aparicion
        myMap = mapOf("July" to 1, "Pedro" to 2, "Sara" to 5)

        var myMap2 = mutableMapOf("July" to 1, "Pedro" to 2, "Sara" to 5)
        myMap2["Ana"] = 7
        myMap2.put("Maria", 8)

        myMap2.remove("Ana")
        println(myMap2)
    }

    /* Tipo for/while */
    fun bucles()
    {
        val myArray: List<String> = listOf("Hola", "makers", "este es mi array")
        val myMap:MutableMap<String, Int> = mutableMapOf("July" to 1, "Pedro" to 2, "Sara" to 5)

        //For
        for(myString in myArray){ println(myString) }

        for(myElement in myMap){ println("${myElement.key}-${myElement.value}") }

        for(x in 0..10) { println(x) }              // Hasta 10
        for(x in 0 until 10) { println(x) }         // Hasta 9
        for(x in 0..10 step 2) { println(x) }       // Incremento + 2
        for(x in 10 downTo 0 step 3) { println(x) }  // Hacia atras

        //While
        var x = 0
        while( x < 10){ x++}
    }

    /* Null Safety */
    fun nullSafety()
    {
        //var myString = "Mi String"
        //myString = null      error de compilacion
        var mySafetyString: String? = "Mi safety string"
        mySafetyString = null // No da error de compilacion

        print(mySafetyString)

        if(mySafetyString != null){println(mySafetyString!!)}

        // Safe call
        println(mySafetyString!!.length)
        println(mySafetyString?.length)

        mySafetyString?.let{
            println(it)  // it es mySafetyString
        } ?: run{
            println(mySafetyString!!)
        }
    }

    /* Funciones */
    fun funciones()
    {
        //Imprime 3 veces hola
        sayHello()
        sayHello()
        sayHello()

        sayMyNameAndAge("Julio", 36)
        sayMyNameAndAge("Raquel", 30)

        var suma = sumarNumeros(2,3)

        var suma2 = sumarNumeros(2, sumarNumeros(10,4))
    }

    fun sayHello()
    {
        println("Hola!")
    }

    fun sayMyNameAndAge(name:String, edad:Int)
    {
        println("Hola mi nombre es ${name} y tengo ${edad} años")
    }

    fun sumarNumeros(num1:Int, num2:Int): Int
    {
        return num1 + num2
    }

    /* Clases */
    fun clases()
    {
        val july = Programer("July",
                        36,
                        arrayOf(Programer.Language.KOTLIN, Programer.Language.Cpp))

        println(july.name)
        july.edad = 35 // edad es de tipo var

        val raquel = Programer("Raquel",
                30,
                      arrayOf(Programer.Language.TODO, Programer.Language.Cpp),
                      arrayOf(july))
        july.code()
        raquel.code()

        println("${raquel.friend?.first()?.name} es amigo de ${raquel.name}")
    }
}