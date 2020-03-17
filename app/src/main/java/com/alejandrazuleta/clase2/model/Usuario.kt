package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuarios")
class Usuario(
    @PrimaryKey @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="nombre") val nombre: String = "",
    @ColumnInfo(name ="facultad") val facultad: String = "",
    @ColumnInfo(name ="programa") val programa: String = "",
    @ColumnInfo(name ="promedio") val promedio: Double = 0.0,
    @ColumnInfo(name ="avance") val avance: Int = 0,
    @ColumnInfo(name ="total") val total: Int = 0,
    @ColumnInfo(name ="citacion") val citacion: String = ""
)