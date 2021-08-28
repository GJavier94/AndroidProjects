package com.example.contentProviderClient.activities

import android.content.ContentResolver
import android.content.ContentValues
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelUpdateActivity :ViewModel(){

    internal var permissionToWriteContacts: Boolean = false
    internal var attrDisplayUpdateName:String = ""
    internal var attrNameNew: String = ""

    internal val attrResultUpdate:MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    //instead of a projection we need to write a ContentValues object with the new values we want to set

    internal var selectionClause:String? = "${ ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME} = ?"
    internal var selectionArgs:Array<String> = arrayOf("")


    override fun onCleared() {

    }


    fun update(contentResolver: ContentResolver) {
        val updateValues = ContentValues().apply{
            put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, this@ViewModelUpdateActivity.attrNameNew )
        }

        val numRows = contentResolver.update(
            ContactsContract.Data.CONTENT_URI,   // the user dictionary content URI
            updateValues,                      // the columns to update
            selectionClause,                   // the column to select on
            selectionArgs                      // the value to compare to
        )
        attrResultUpdate.value = "numRows: $numRows affected"
        Log.i(TAG, "numRows: $numRows")

    }
    companion object{
        val TAG = "ViewModelUpdLogger"
    }
}
