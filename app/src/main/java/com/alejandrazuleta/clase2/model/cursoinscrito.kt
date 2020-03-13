package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_cursoinscrito")
class cursoinscrito(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="idusuario") val idu: String = ""
)