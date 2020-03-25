package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_evaluaciones")
class evaluacion (
    @PrimaryKey @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name = "nombre") val nombre: String = "",
    @ColumnInfo(name ="porcentaje") val porcentaje: Int = 0
)