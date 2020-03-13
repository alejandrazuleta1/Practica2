package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_facultad")
class facultad(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="nombre") val nombre: String = "",
    @ColumnInfo(name ="calendarioac") val myArray: Array<String> = arrayOf<String>("")
)