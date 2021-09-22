package com.example.databindingapp.converters

import android.util.Log
import android.widget.EditText
import androidx.databinding.InverseMethod
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object DateConverter {
    const val TAG = "DateConverter"
    @InverseMethod("stringToDate")
    @JvmStatic fun dateToString(
        newValue: Date
    ):String{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd")
        Log.i(TAG, "newValue = ${dateFormat.format(newValue)}")
        val strDate: String = dateFormat.format(newValue)

        return strDate
    }

    @JvmStatic fun stringToDate(
        newValue: String,
        ):Date{
        Log.i(TAG, " newValue = ${newValue}")
        val listStrings = newValue.split("-")
        if(listStrings.size >= 3){
            val year = listStrings[0].toInt()
            val month = listStrings[1].toInt()
            val day = listStrings[2].toInt()
            return Calendar.getInstance().run {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month )
                set(Calendar.DAY_OF_MONTH, day)
                Date(this.timeInMillis)
            }
        }
        return Date("1999-03-03")
    }
}