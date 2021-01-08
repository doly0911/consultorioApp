package co.edu.udea.saludpublica.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "usuario_table")
data class Usuario (

    @PrimaryKey(autoGenerate = true)
    var usuarioId : Long = 0L,

    @ColumnInfo(name = "numero_identificacion")
    var numeroIdentificacion : String = "",

    @ColumnInfo(name = "nombres")
    var nombres : String = "",

    @ColumnInfo(name = "apellidos")
    var apellidos : String = "",

    @ColumnInfo(name = "correo")
    var correo : String = "",

    @ColumnInfo(name = "password")
    var password : String = ""

) : Parcelable