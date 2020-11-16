package com.july.myfirstapplication2

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val key = "MY_KEY"
    private val GOOGLE_SIGN_IN = 100
    private val CALLBACK_MANAGER= CallbackManager.Factory.create()

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
        sesion()
        botonesYtexto()

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
        //https://firebase.google.com/docs/auth/android/start#kotlin+ktx
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

        //https://firebase.google.com/docs/auth/android/google-signin
        // En Android Studio -> Pestaña derecha Grandle->"nombre app"->Task->android->signingReport
        // Ventana de abajo, copiar SHA1

        //En Firebase, rueda dentada->Configuración del proyecto->General-> añadir huella
        // digital y copiar SHA1
        googleButton.setOnClickListener{
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this,googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        //https://developers.facebook.com/
        // Crear aplicacion-> Configuracion-> Informacion basica
        // Signo + añadir producto-> Inicio de sesion con facebook-> Android
        //https://firebase.google.com/docs/auth/android/facebook-login
        facebookButton.setOnClickListener{

            LoginManager.getInstance().logInWithReadPermissions(this,listOf("email"))
            LoginManager.getInstance().registerCallback(CALLBACK_MANAGER,
            object: FacebookCallback<LoginResult>{

                override fun onSuccess(result: LoginResult?) {
                    result?.let{
                        val token = it.accessToken

                        val credential = FacebookAuthProvider.getCredential(token.token)
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                            if(it.isSuccessful){
                                showHome(it.result?.user?.email ?:"", ProviderType.FACEBOOK)
                            }else{
                                showAlert("La autenticacion Facebook ha fallado")
                            }
                        }
                    }
                }

                override fun onCancel() {
                    TODO("Not yet implemented")
                }

                override fun onError(error: FacebookException?) {
                    showAlert("Fallo en facebook")
                }
            })
        }

    }

    override fun onStart() {
        super.onStart()

        authLayout.visibility = View.VISIBLE
    }

    private fun sesion(){
        // Sin edit al final
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider !=null){
            authLayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        CALLBACK_MANAGER.onActivityResult(requestCode, resultCode, data) //Facebook

        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try{
                val account = task.getResult(ApiException::class.java)

                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(account.email ?:"", ProviderType.GOOGLE)
                        }else{
                            showAlert("La autenticacion Google ha fallado")
                        }
                    }
                }
            }catch (e: ApiException){
                showAlert("No se ha podido recuperar la cuenta")
            }


        }
    }

    private fun botonesYtexto(){
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
}