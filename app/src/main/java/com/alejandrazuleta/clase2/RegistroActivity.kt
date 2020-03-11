package com.alejandrazuleta.clase2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.get
import com.alejandrazuleta.clase2.utils.Constantes.Companion.EMPTY
import com.alejandrazuleta.clase2.utils.Constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.et_password
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private var cal=Calendar.getInstance()
    private lateinit var fecha : String //lateinint para no inicializar las variables, pero sí toca decir el tipo de la variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val dataSetListener =object:DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(format,Locale.US)
                fecha =sdf.format(cal.time).toString()
                Log.d("fecha: ",fecha)
            }
        }

        tv_showPicker.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Initializing a String Array
        val ciudades = arrayOf("Bogotá","Medellín","Santa Marta","Cali","Pasto")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            ciudades // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spCiudad.adapter = adapter;

        var ciudad= EMPTY;

        spCiudad.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                ciudad = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback, nothing in this case
            }
        }

        bt_registrar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val password = et_password.text.toString()
            val repPassword = et_repassword.text.toString()

            var sexo ="Masculino"

            if(rb_masculino.isChecked) sexo="Masculino"
            else sexo="Femenino"

            var pasatiempos=EMPTY

            if(cb_cine.isChecked)pasatiempos=pasatiempos+ SPACE +cb_cine.text
            if(cb_gimnasio.isChecked)pasatiempos=pasatiempos+ SPACE +getString(R.string.gimnasio) //otra opcion para que traduzca todos los strings
            if(cb_leer.isChecked)pasatiempos=pasatiempos+ SPACE +cb_leer.text
            if(cb_series.isChecked)pasatiempos=pasatiempos+ SPACE +cb_series.text

            if (nombre.isEmpty()||correo.isEmpty()||password.isEmpty()||repPassword.isEmpty()||telefono.isEmpty()){
                Toast.makeText(this,getString(R.string.msg_error_campos_vacios),Toast.LENGTH_SHORT).show()
            }else if (password!=repPassword){
                Toast.makeText(this,getString(R.string.contrasenasdiferentes),Toast.LENGTH_SHORT).show()
            }else if (password.length<6){
                Toast.makeText(this,"La contraseña es muy corta, debe escribir mínimo 6 dígitos",Toast.LENGTH_SHORT).show()
            }
            else if (isEmailValid(correo)) {
                /*tv_resultado.text = "Nombre: " + nombre +
                        "\nCorreo: " + correo +
                        "\nTeléfono: " + telefono +
                        "\nContraseña: " + password +
                        "\nGénero: "+ sexo +
                        "\nPasatiempos: "+ pasatiempos +
                        "\nFecha de nacimiento: "+ fecha +
                        "\nLugar de nacimiento: " + ciudad*/

                var intent = Intent()
                intent.putExtra("email",correo)
                intent.putExtra("password",password)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }else {
                Toast.makeText(this,"El correo tiene caracteres no válidos",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }

    fun showDatePickerDialog(view: View) {

    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }


}

