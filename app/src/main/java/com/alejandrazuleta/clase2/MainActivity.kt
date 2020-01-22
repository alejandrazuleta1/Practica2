package com.alejandrazuleta.clase2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correo =""
    var password =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datosRecebidos =intent.extras
        if (datosRecebidos != null){
            correo = datosRecebidos?.getString("correo").toString()
            password = datosRecebidos?.getString("password").toString()
            tv_resultado.text = correo
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.i_cerrar){
            var intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("email",correo)
            intent.putExtra("password",password)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


}

