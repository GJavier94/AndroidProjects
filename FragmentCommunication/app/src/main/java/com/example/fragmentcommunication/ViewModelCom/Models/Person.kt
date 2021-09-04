package com.example.fragmentcommunication.ViewModelCom.Models

import android.os.Parcel
import android.os.Parcelable

data class Person(var name:String, var surname:String, var age:Int): Parcelable {

    private constructor(source:Parcel):this(
        source.readString() ?: "unknown",
        source.readString()?: "unknown",
        source.readInt()
    )

    companion object{
        @JvmField val CREATOR:Parcelable.Creator<Person> = object:Parcelable.Creator<Person>{
            override fun createFromParcel(source: Parcel): Person {
                return Person(source)
            }

            override fun newArray(size: Int): Array<Person?> {
                return arrayOfNulls<Person>(size)
            }

        }
    }
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeString(name)
            writeString(surname)
            writeInt(age)
        }
    }

    override fun describeContents(): Int {
        return 0
    }
}
