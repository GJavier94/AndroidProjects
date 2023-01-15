package com.example.commonintentstakephoto

import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ViewModelMainAct:ViewModel() {
    internal var file: File? = null
    internal var nameImageFile: String? = null

    fun createImageFile(applicationContext: Context){
        // we will create a nunique name by using timestamp
        // get the storage directory by calling the environment static class variable directory_pictures
        // use the method of context to create a fiel with that path
        // use the previous file as directory for the file created
        // create a temporary file so that  it has that name
         // get the absolute path of the tempory file

        val timeStamp =  SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        Log.i(TAG, "Environment.DIRECTORY_PICTURES ${Environment.DIRECTORY_PICTURES}")
        val storageDir:File? = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        Log.i(TAG,"storageDir ${storageDir?.absolutePath}" )
        val file = File.createTempFile("JPEG_${timeStamp}_",".jpg",storageDir)
        Log.i(TAG,"file.abspath ${file.absolutePath}" )
        this.nameImageFile = file.absolutePath
        this.file = file
    }
    fun addToGalleryProvider(nameImageFile: String, applicationContext: Context) {
        //this doesn't save the photo cause the directory is internal for the app in the external storage
        Log.i(TAG,"Saving photo...")
        val file = File(nameImageFile)
        Log.i(TAG,"${file.absolutePath}")
        Log.i(TAG,"${file.toString()}")

        MediaScannerConnection.scanFile(applicationContext,
        arrayOf(file.toString()),
        null, object:MediaScannerConnection.OnScanCompletedListener {
                override fun onScanCompleted(path: String?, uri: Uri?) {
                    Log.i(TAG,"The scan on $path with $uri has been completed")
                }

            })

    }

    fun getUriContentForFile(applicationContext: Context): Uri? {
        var uriFile:Uri? = null
        if(this.file != null ){
            uriFile = FileProvider.getUriForFile(
                applicationContext,
                "com.example.commonintentstakephoto.FileProvider"
                ,
                file!!
            )
        }
        return uriFile
    }

    companion object{
        const val TAG = "ViewModelMainAct"
    }
}
