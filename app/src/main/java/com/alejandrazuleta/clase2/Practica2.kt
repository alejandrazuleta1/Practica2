package com.alejandrazuleta.clase2

import android.app.Application
import androidx.room.Room
import com.alejandrazuleta.clase2.model.room.UsuarioDataBase

class Practica2: Application() {
    companion object{
        lateinit var database: UsuarioDataBase
    }

    override fun onCreate() {
        super.onCreate()
        Practica2.database = Room.databaseBuilder(
            this,
            UsuarioDataBase::class.java,
            "usuario_DB"
        ).allowMainThreadQueries().build()
    }
}