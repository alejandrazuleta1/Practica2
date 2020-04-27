package com.alejandrazuleta.clase2.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandrazuleta.clase2.model.Usuario
import com.alejandrazuleta.clase2.model.room.UsuarioDAO

@Database(entities = [Usuario::class], version = 2)
abstract class UsuarioDataBase : RoomDatabase() {

    abstract fun UsuarioDAO(): UsuarioDAO

}