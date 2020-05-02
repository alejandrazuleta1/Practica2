package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tabla_sesion")
class sesion(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="dia") val dia: Int = 0,
    @ColumnInfo(name ="hora") val hora: HashMap<String, Int> = hashMapOf("inicio" to 0, "final" to 0),
    @ColumnInfo(name ="aula") val aula: String = ""
): Serializable