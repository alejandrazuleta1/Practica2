package com.alejandrazuleta.clase2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var emailRec=""
    var passwordRec=""

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN=1

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var datosRecebidos =intent.extras
        if (datosRecebidos != null){
            emailRec = datosRecebidos.getString("email").toString()
            passwordRec = datosRecebidos.getString("password").toString()
        }

        //verifico si ya hay usuario logeado
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            // already signed in
            goToMainActivity()
        }

        bt_login.setOnClickListener(this)
        bt_loginGoogle.setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


    }

    public override fun onStart() {
        super.onStart()
    }

    private fun goToMainActivity() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.bt_login -> {
                var emailDig = et_email.text.toString()
                var passwordDig = et_password.text.toString()

                if(emailDig=="") {
                    Toast.makeText(this, "Escriba el email y la contraseña", Toast.LENGTH_SHORT).show()
                }else{

                    auth.signInWithEmailAndPassword(emailDig, passwordDig)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LoginActivity", "signInWithEmail:success")
                                val currentUser = auth.currentUser
                                if (currentUser != null) {
                                    goToMainActivity()
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                                if(task.exception!!.message.equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                                    Toast.makeText(this,"Usuario no registrado",Toast.LENGTH_SHORT).show()
                                }
                                if(task.exception!!.message.equals("A network error (such as timeout, interrupted connection or unreachable host) has occurred.")){
                                    Toast.makeText(this,"Error de red",Toast.LENGTH_SHORT).show()
                                }
                                if(task.exception!!.message.equals("The password is invalid or the user does not have a password.")){
                                    Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                }
            }
            R.id.bt_loginGoogle->signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("SingInGoogle", "Google sign in failed", e)
                Toast.makeText(this,"Google sign in failed: "+e,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    goToMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this,"Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
