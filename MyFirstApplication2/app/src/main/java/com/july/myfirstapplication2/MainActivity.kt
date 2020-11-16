package com.july.myfirstapplication2

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val key = "MY_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_MyFirstApplication2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Google analytics
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion de firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //Setup
        setup()

        // Formato de fuentes
        //textView.text = "Cambiar el texto"
        //textView.text = getString(R.string.Hello)
        textView.typeface = Typeface.createFromAsset(assets,"fonts/Starjedi.ttf")
        textView2.typeface = Typeface.createFromAsset(assets,"fonts/Starjhol.ttf")

        //Obtenemos el PreferenceManager
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        // Recuperar las preferencias
        getButton.setOnClickListener{
            val myPref = prefs.getString(key,"No hay un valor para esta clave")
            myPref?.let{ showAlert(myPref) }
        }

        // Guarda las preferencias
        putButton.setOnClickListener{
            //Modo edicion
            val editor = prefs.edit()

            editor.putString(key, "Mi valor")
            editor.apply()
            showAlert("Hemos guardado el valor $key")
        }

        // Borra las preferencias
        delButton.setOnClickListener{
            val editor = prefs.edit()

            editor.remove(key)
            editor.apply()
            showAlert("Hemos borrado el valor $key")
        }
    }

    private fun showAlert(message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String, provider:ProviderType){
        val homeIntent: Intent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    private fun setup(){
        title = "Autenticacion"

        regButton.setOnClickListener{
            if(emailText.text.isNotEmpty() && passwText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        emailText.text.toString(),
                        passwText.text.toString()).addOnCompleteListener{

                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?:"",
                            ProviderType.BASIC)
                        }else{
                            showAlert("Algo ha fallado")
                        }
                    }
            }
        }

        logButton.setOnClickListener{
            if(emailText.text.isNotEmpty() && passwText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        emailText.text.toString(),
                        passwText.text.toString()).addOnCompleteListener{

                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?:"",
                                ProviderType.BASIC)
                        }else{
                            showAlert("Algo ha fallado")
                        }
                    }
            }
        }
    }
}