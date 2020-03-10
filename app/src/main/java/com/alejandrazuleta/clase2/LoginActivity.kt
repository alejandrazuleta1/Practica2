package com.alejandrazuleta.clase2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.reflect.typeOf

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

        tv_registrar.setOnClickListener {
            var intent = Intent(this,RegistroActivity::class.java)
            startActivityForResult(intent,1)
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
                            goToMainActivity()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1 && resultCode==Activity.RESULT_CANCELED)
            Toast.makeText(this,"No se registró",Toast.LENGTH_SHORT).show()
        if(requestCode==1 && resultCode==Activity.RESULT_OK)
            //Toast.makeText(this,data?.extras?.getString("email"),Toast.LENGTH_SHORT).show()
            //Toast.makeText(this,data?.extras?.getString("password"),Toast.LENGTH_SHORT).show()
            emailRec=data?.extras?.getString("email").toString()
            passwordRec=data?.extras?.getString("password").toString()
        super.onActivityResult(requestCode, resultCode, data)
    }

}
