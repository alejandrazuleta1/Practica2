package com.alejandrazuleta.clase2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.reflect.typeOf

class LoginActivity : AppCompatActivity() {

    var emailRec=""
    var passwordRec=""

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
            /*
            if(emailDig=="") {
                Toast.makeText(this, "Escriba el email y la contraseña", Toast.LENGTH_SHORT).show()
            }else{
                if (emailRec == emailDig) {
                    if (passwordRec == passwordDig) {
                        //abrir el main*/
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("correo", emailDig)
                        intent.putExtra("password", passwordDig)
                        startActivity(intent)
                        finish()
                   /* } else {
                        Toast.makeText(this, passwordRec, Toast.LENGTH_SHORT).show()
                        //Toast.makeText(this,"Contraseña equivocada",Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(this,"No se encuentra registrado ese correo",Toast.LENGTH_SHORT).show()
                }
            }*/
        }
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
