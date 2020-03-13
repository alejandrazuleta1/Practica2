package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_sesion")
class sesion(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="dia") val nombre: String = "",
    @ColumnInfo(name ="hora") val profesor: String = "",
    @ColumnInfo(name ="aula") val creditos: String = ""
)