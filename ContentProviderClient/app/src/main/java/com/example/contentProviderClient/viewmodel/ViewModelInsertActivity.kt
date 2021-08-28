package com.example.contentProviderClient.viewmodel

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.ContentUris

import android.provider.ContactsContract




class ViewModelInsertActivity: ViewModel() {

    internal var attrDisplayName = ""
    internal val attrResultInsert:MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    internal var permissionToWriteContacts = false


    /*
    In order to insert a value into the content provider  we just need two things:
    1.- a ContentValue object in which case each column value of the row is registered through the method .put(Key(column), value )
    2.- call the insert methods of the contentResolver specifying the URI ( table route ) and the content value object -> the new record
    Return value
    the URI of the new record -> content://user_dictionary/words/<id_value>     to getIt ContentUri.parseId(URI)
    null if  couldn't be inserted or crashes
     */
    /* Some content providers provide methods to insert new element without using insert()
        in this case addMessage
     */


    fun insert(contentResolver: ContentResolver): Uri? {


        val contentValues = ContentValues()
        //we insert a new contact without any values
        var uriResult:Uri? = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, contentValues)
        Log.i(TAG, "insertion without values uriResult: $uriResult")


        val iDContact:Long = ContentUris.parseId(uriResult!!)

        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, iDContact)
        // Each contact must has an mime type to avoid java.lang.IllegalArgumentException: mimetype is required error.
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, this.attrDisplayName)
        uriResult  = contentResolver.insert(ContactsContract.Data.CONTENT_URI, contentValues)
        Log.i(TAG, "insertion with values uriResult: $uriResult")

        when(uriResult){
            null -> {
                this.attrResultInsert.value  = "There was an error while processing the insert "
                Log.i(TAG,  "There was an error while processing the insert ")
            }

            else -> {
                this.attrResultInsert.value = "The uriResult is: $uriResult"
                Log.i(TAG,  "The uriResult is: $uriResult")
            }
        }
        return uriResult
    }

    override fun toString(): String {
        return "ViewModelInsertActivity(attrDisplayName='$attrDisplayName', attrResultInsert=${attrResultInsert.value})"
    }


    companion object{
        const val TAG = "ViewModelInsActLogger"
    }
}
