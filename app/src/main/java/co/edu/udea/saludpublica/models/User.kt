package co.edu.udea.saludpublica.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    var userId : Long = 0L,

    @ColumnInfo(name = "id_number")
    var idNumber : String = "",

    @ColumnInfo(name = "names")
    var names : String = "",

    @ColumnInfo(name = "last_names")
    var lastNames : String = "",

    @ColumnInfo(name = "email")
    var email : String = "",

    @ColumnInfo(name = "password")
    var password : String = ""

) : Parcelable