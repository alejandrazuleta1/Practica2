package com.alejandrazuleta.clase2.model.room

import androidx.room.*
import com.alejandrazuleta.clase2.model.Usuario

@Dao
interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: Usuario)

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Delete
    fun deleteUsuario(usuario: Usuario)

    @Query("SELECT * FROM tabla_usuarios")
    fun loadAllUsers(): Array<Usuario>

    @Query("SELECT * FROM tabla_usuarios WHERE id LIKE :id")
    fun searchUsuario(id: String): Usuario
}