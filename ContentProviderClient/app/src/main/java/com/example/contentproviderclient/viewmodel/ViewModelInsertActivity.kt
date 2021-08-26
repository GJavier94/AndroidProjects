package com.example.contentproviderclient.viewmodel

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.UserDictionary
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelInsertActivity: ViewModel() {
    internal val uriResult: MutableLiveData<Uri> by lazy{
        MutableLiveData<Uri>()
    }

    internal var attrAppId= MutableLiveData<String>("")

    internal var attrLocale = MutableLiveData<String>("")
    internal var attrWord = MutableLiveData<String>("")
    internal var attrFrequency = MutableLiveData<String>("")
    internal var attrResultInsert = MutableLiveData<String>("Result:")

    /*
    In order to insert a value into the content provider  we just need two things:
    1.- a ContentValue object in which case each column value of the row is registered through the method .put(Key(column), value )
    2.- call the insert methods of the contentResolver specifiying the URI ( table route ) and the content value object -> the new record
    Return value
    the URI of the new record -> content://user_dictionary/words/<id_value>     to getIt ContentUri.parseId(URI)
    null if  couldn't be inserted or crashes
     */
    fun insert(contentResolver: ContentResolver): Uri? {
        val contentValue = ContentValues().apply {
            put(UserDictionary.Words.APP_ID,attrAppId.value)
            put(UserDictionary.Words.LOCALE,attrLocale.value)
            put(UserDictionary.Words.WORD,attrWord.value)
            put(UserDictionary.Words.FREQUENCY, attrFrequency.value)
        }
        val uriResult = contentResolver.insert(
            UserDictionary.Words.CONTENT_URI,
            contentValue
        )
        when(uriResult){
            null -> {
                this.attrResultInsert.value  = "There was an error while processing the insert "
                Log.i(TAG,  "There was an error while processing the insert ")
            }

            else -> {
                this.uriResult?.value  = uriResult
                this.attrResultInsert.value = "The uriResult is: ${this.uriResult?.value}"
            }
        }
        return uriResult
    }

    override fun toString(): String {
        return "ViewModelInsertActivity(uriResult=$uriResult, attrAppId=${attrAppId.value}, attrLocale=${attrLocale.value}, attrWord=${attrWord.value}, attrFrequency=${attrFrequency.value}, attrResultInsert=${attrResultInsert.value})"
    }


    companion object{
        const val TAG = "ViewModelInsActLogger"
    }
}
