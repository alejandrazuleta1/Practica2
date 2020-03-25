package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_curso")
class curso(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="nombre") val nombre: String = "",
    @ColumnInfo(name ="profesor") val profesor: String = "",
    @ColumnInfo(name ="creditos") val creditos: Int = 0,
    @ColumnInfo(name ="evalyporc") val evalyporc: List<evaluacion> = arrayListOf(),
    @ColumnInfo(name ="sesionesporsem") val sesionesporsem: List<String> = arrayListOf()
)