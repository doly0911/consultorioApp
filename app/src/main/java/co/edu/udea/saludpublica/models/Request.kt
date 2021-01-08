package co.edu.udea.saludpublica.models

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import co.edu.udea.saludpublica.converters.ChannelConverter
import co.edu.udea.saludpublica.converters.PriorityConverter
import co.edu.udea.saludpublica.enums.ChannelEnum
import co.edu.udea.saludpublica.enums.PriorityEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "request_table")
data class Request(
    @PrimaryKey(autoGenerate = true)
    var requestId : Long = 0L,

    @ForeignKey
        (entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["owner"],
        onDelete = CASCADE)
    var owner: Long = 0L,

    @ColumnInfo(name = "topic")
    var topic: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "priority")
    @TypeConverters(PriorityConverter::class)
    var priority: PriorityEnum = PriorityEnum.BAJA,

    @ColumnInfo(name = "channel")
    @TypeConverters(ChannelConverter::class)
    var channel : ChannelEnum = ChannelEnum.CORREO

) : Parcelable