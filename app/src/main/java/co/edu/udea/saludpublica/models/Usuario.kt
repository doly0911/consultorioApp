package co.edu.udea.saludpublica.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usuario (val correo : String, val password : String ) : Parcelable