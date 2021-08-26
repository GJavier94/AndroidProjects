package com.example.contentproviderclient.viewmodel

import android.content.ContentResolver
import android.database.Cursor
import android.provider.UserDictionary
import android.util.Log
import android.widget.SimpleCursorAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contentproviderclient.R

class ViewModelRetrieveActivity: ViewModel() {
    /*internal var mCursor: Cursor
    internal var arrayIdsRow: Array<Int>
    internal var arraryColumnsNames: Array<String>

    */
    //info about the query for the cursor
    internal var cursor:Cursor? = null
    internal var searchText: MutableLiveData<String> = MutableLiveData<String>()
    internal val projection: Array<String> = arrayOf( UserDictionary.Words._ID,
        UserDictionary.Words.WORD,
        UserDictionary.Words.LOCALE)
    internal var selectionClause:String? = "${UserDictionary.Words.WORD} = ?"
    internal var selectionArgs:Array<String> = arrayOf("")

    //info about the attrs of the adapter
    internal val listColumnsAdapter:Array<String> = arrayOf(UserDictionary.Words.WORD,
        UserDictionary.Words.LOCALE)
    internal val listIdItems:IntArray = intArrayOf(R.id.dictionary_word, R.id.dictionary_locale)


    internal var adapter: SimpleCursorAdapter? = null


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
            UserDictionary.CONTENT_URI,
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



