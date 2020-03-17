package com.alejandrazuleta.clase2.modelRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandrazuleta.clase2.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioDataBase : RoomDatabase() {

    abstract fun UsuarioDAO(): UsuarioDAO

}