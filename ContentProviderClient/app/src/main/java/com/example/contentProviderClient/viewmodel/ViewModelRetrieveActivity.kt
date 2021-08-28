package com.example.contentProviderClient.viewmodel

import android.content.ContentResolver
import android.database.Cursor
import android.provider.Telephony
import android.util.Log
import android.widget.SimpleCursorAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contentProviderClient.R

class ViewModelRetrieveActivity: ViewModel() {

    //info about the query for the cursor
    internal var cursor:Cursor? = null
    internal var searchId: MutableLiveData<String> = MutableLiveData<String>()
    internal val projection: Array<String> = arrayOf( Telephony.Sms.Inbox._ID,
        Telephony.Sms.CREATOR,
        Telephony.Sms.Inbox.DATE,
        Telephony.Sms.Inbox.BODY)
    internal var selectionClause:String? = "${ Telephony.Sms.Inbox._ID} = ?"
    internal var selectionArgs:Array<String> = arrayOf("")

    //info about the attrs of the adapter
    internal val listColumnsAdapter:Array<String> = arrayOf(Telephony.Sms.Inbox._ID,
        Telephony.Sms.CREATOR,
        Telephony.Sms.Inbox.DATE,
        Telephony.Sms.Inbox.BODY)
    internal val listIdItems:IntArray = intArrayOf(R.id.Sms_id, R.id.sms_Creator,R.id.sms_sent_date,R.id.sms_text)


    internal var adapter: SimpleCursorAdapter? = null

    internal var permissionToReadContentProvider = false

    override fun onCleared() {

    }


    //1.- to have permission from the manifest to use the content provider
    // <uses-permission android:name = "android.permission.READ_USER_DICTIONARY"/>
    //2.-In order to establish communication to the contentProvider we need to call the query method of him
    // via the query method from contentResolver -> ContentResolver is an API to manage binding between source data and other app entry

    /**
     * The query methods needs
     * cursor = contentResolver.query(
    UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
    projection,                        // The columns to return for each row --> arrayOfStrings(col1,coli)
    selectionClause,                   // Selection criteria      String for where clause
    selectionArgs.toTypedArray(),      // Selection criteria      array with arguments to replace in selectionsClause to avoid SQLInjection
    sortOrder                          // The sort order for the returned rows --> order of the rows
    )
     */



    fun doQuery(contentResolver: ContentResolver) {
        if(contentResolver==null) return
        this.cursor = contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI,
            this.projection,
            selectionClause,
            selectionArgs,
            null
            )

        Log.i(TAG, "${this.cursor?.count} ${this.cursor?.columnCount}")

    }
    companion object{
        val TAG = "ViewModelMLogger"
    }
}



