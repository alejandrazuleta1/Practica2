package com.alejandrazuleta.clase2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.facultad
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    var correo =""
    var password =""

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("facultades")

        val idfacultad = myRef.push().key
        val myarray = arrayListOf<String>("1","2")

        val facultad = facultad(idfacultad!!,"Ingenieria", myarray as Array<String>)
        myRef.child(idfacultad).setValue(facultad)
        */


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datosRecebidos =intent.extras
        if (datosRecebidos != null) {
            correo = datosRecebidos?.getString("correo").toString()
            password = datosRecebidos?.getString("password").toString()
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_perfil, R.id.nav_notas, R.id.nav_calculadora,
                R.id.nav_horario, R.id.nav_eventos, R.id.nav_cerrarsesion
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_cerrarsesion){

            var intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("email",correo)
            intent.putExtra("password",password)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
