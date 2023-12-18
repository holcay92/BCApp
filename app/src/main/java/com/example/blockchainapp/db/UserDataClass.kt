package com.example.blockchainapp.db // ktlint-disable filename

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    var itemId: Int,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "surname")
    var surname: String = "",
    @ColumnInfo(name = "ssn")
    var ssn: String = "",
    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String = "",
    @ColumnInfo(name = "itemTitle")
    var itemTitle: String = "",
    @ColumnInfo(name = "itemDescription")
    var itemDescription: String?,
    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(itemId)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(ssn)
        parcel.writeString(phoneNumber)
        parcel.writeString(itemTitle)
        parcel.writeString(itemDescription)
        parcel.writeValue(itemPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
