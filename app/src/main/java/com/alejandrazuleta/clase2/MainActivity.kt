package com.alejandrazuleta.clase2

import android.app.AlertDialog
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
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                R.id.nav_horario, R.id.nav_eventos
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
        if(item.itemId == R.id.nav_cerrarsesion) {
            //preguntar si está seguro
            val alertDialog: AlertDialog? = this@MainActivity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Estás seguro que deseas cerrar sesión?")
                    setPositiveButton(
                        "Sí"
                    ) { dialog, id ->
                        //ciero sesion en firebase
                        val auth = FirebaseAuth.getInstance()
                        auth.signOut()
                        goToLoginActivity()
                    }
                    setNegativeButton(
                        "No"
                    ) { dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToLoginActivity() {
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
