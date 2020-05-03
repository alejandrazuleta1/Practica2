package com.alejandrazuleta.clase2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tabla_evento")
class evento(
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name ="idusuario") val idu: String = "",
    @ColumnInfo(name ="nombre") val nombre: String = "",
    @ColumnInfo(name ="descripcion") val descripcion: String = "",
    @ColumnInfo(name ="fechainicio") val fechai: String = "",
    @ColumnInfo(name ="horainicio") val horai: String = "",
    @ColumnInfo(name ="fechafinal") val fechaf: String = "",
    @ColumnInfo(name ="horafinal") val horaf: String = "",
    @ColumnInfo(name ="nombrecurso") val nombrecurso: String = ""
):  Serializable