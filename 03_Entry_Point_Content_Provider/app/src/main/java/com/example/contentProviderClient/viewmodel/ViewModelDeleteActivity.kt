package com.example.contentProviderClient.viewmodel

import android.content.ContentResolver
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelDeleteActivity: ViewModel() {

    //instead of a projection we need to write a ContentValues object with the new values we want to set

    internal var selectionClause:String? = "${ ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME} = ?"
    internal var selectionArgs:Array<String> = arrayOf("")


    val attrResultDelete: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var attrDisplayName: String = ""
    var permissionToWriteContacts: Boolean  = false

    fun delete(contentResolver: ContentResolver?) {
        val numRows = contentResolver?.delete(
            ContactsContract.Data.CONTENT_URI,
            selectionClause,
            selectionArgs
        )
        attrResultDelete.value = "numRows: $numRows affected"
        Log.i(ViewModelUpdateActivity.TAG, "numRows: $numRows")

    }
}
