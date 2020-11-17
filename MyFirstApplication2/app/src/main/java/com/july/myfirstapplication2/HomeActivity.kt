package com.july.myfirstapplication2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.RuntimeException

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email?: "", provider?:"")

        //Guardado de datos

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        // Remote Config
        errorButton.visibility = View.INVISIBLE
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
            if(it.isSuccessful){
                val showErrrButton = Firebase.remoteConfig.getBoolean("show_error_button")
                val errorbuttonText = Firebase.remoteConfig.getString("error_button_text")

                if(showErrrButton){
                    errorButton.visibility = View.VISIBLE
                }

                errorButton.text = errorbuttonText
            }
        }
    }

    private fun setup(email:String, provider:String){
        title = "Inicio"

        emailtextView.text = email
        providertextView.text = provider

        cerrarbutton.setOnClickListener {
            //Borrado de datos en preferencias
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            if(provider == ProviderType.FACEBOOK.name){
                LoginManager.getInstance().logOut()
            }

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        errorButton.setOnClickListener {
            FirebaseCrashlytics.getInstance().setUserId(email)
            FirebaseCrashlytics.getInstance().setCustomKey("provider", provider)

            // Enviar log de contexto
            FirebaseCrashlytics.getInstance().log("Se ha pulsado el boton forzar error")

            // Forzando error
            throw RuntimeException("Forzando un error")
        }

        savebutton.setOnClickListener {
            val user = hashMapOf("provider" to provider,
                "address" to addressTextView.text.toString(),
                "phone" to phoneTextView.text.toString())

            db.collection("users").document(email).set(user)
                .addOnSuccessListener { Log.d("Base Datos", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("Base Datos", "Error writing document", e) }

        }

        getbutton.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener{
                addressTextView.setText(it.get("address") as String?)
                phoneTextView.setText(it.get("phone") as String?)
            }
        }

        deletebutton.setOnClickListener {
            db.collection("users").document(email).delete()
        }
    }

}