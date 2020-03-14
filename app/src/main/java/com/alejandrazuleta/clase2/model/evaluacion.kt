package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo

class evaluacion (
    @ColumnInfo(name = "nombre") val nombre: String = "",
    @ColumnInfo(name ="porcentaje") val porcentaje: Int = 0
)