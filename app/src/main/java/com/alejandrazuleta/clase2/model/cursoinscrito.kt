package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "tabla_cursoinscrito")
class cursoinscrito(
    @ColumnInfo(name = "idusuario") val idusuario: String = "",
    @ColumnInfo(name = "idcurso") val idcurso: String = "",
    @ColumnInfo(name = "notas") val notas: List<Int> = arrayListOf<Int>()
): Serializable