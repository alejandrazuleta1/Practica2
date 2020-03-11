package com.alejandrazuleta.clase2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var emailRec=""
    var passwordRec=""

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var datosRecebidos =intent.extras
        if (datosRecebidos != null){
            emailRec = datosRecebidos?.getString("email").toString()
            passwordRec = datosRecebidos?.getString("password").toString()
        }

        //verifico si ya hay usuario logeado
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            // already signed in
            goToMainActivity()
        }

        bt_login.setOnClickListener {
            var emailDig = et_email.text.toString()
            var passwordDig = et_password.text.toString()

            if(emailDig=="") {
                Toast.makeText(this, "Escriba el email y la contraseña", Toast.LENGTH_SHORT).show()
            }else{

                val auth = FirebaseAuth.getInstance()

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
                        }
                    }
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
