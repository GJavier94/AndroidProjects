package com.example.storageapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageManager.ACTION_MANAGE_STORAGE
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import java.io.File
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class StorageDateHandler(var context:Context) {
    var file:File
    val dateArray:MutableList<String> = mutableListOf<String>()

    init {
        //Printing all directory files and  files on the root to delete it

        /**
         * Context
         * has a set of methods to work with files
         * as we know for app-storage specific we have 4 directories for each app
         * Internal
         *  Persisted files: getFileDir()
         *  Cache files: getCacheFileDir()
         * External
         *  Persisted files: getExternalFileDir()
         *  Cache files: getExternalFileDir()
         *
         * fileList() -> to list all the files into the root directory
         * deleteFile() -> to delete a file
         *
         * mkdir to create a new directory
         * context.filesDir.listFiles() to show all the files in this parent directory
         * Directories as far as i can see are also considered files within context file variables
         * */

        // let's create Nested Directories
        /*
        repeat(10){
                position ->
            File(context.filesDir, "privateFiles$position").mkdir()
        }

        context.filesDir.listFiles().forEach {
            Log.i(TAG, it.absolutePath)
        }

         */

        //deleting the files

        file = File(context.filesDir,SOURCE_FILE_DATE_LAUNCHED)


        if(!file.exists()){
            val flag = file.createNewFile()
            if(flag){
                Log.i(TAG, "Fail to create the new file")
            }
        }
        else{
            //the file already exist we are just adding a new DateLaunched
            Log.i(TAG, "The file already exists")

        }
        Log.i(TAG, "calling buffered Reader")

        file.bufferedReader().useLines {
                lines ->
            Log.i(TAG, "going through the sequence")
            lines.iterator().forEach {
                    date ->
                //Log.i(TAG, "line: $date")

                val regex = """\n""".toRegex()
                if(!date.matches(regex)){
                    this.dateArray.add(date)
                }
            }
        }

        /**
         * The two directories for cache files are obtained by
         * context.cacheDir
         * context.externalCacheDir
         *
         * we can use the File API  static  method
         * .createTempFile -> to create a cache file
         */

        Log.i(TAG, context.cacheDir.absolutePath  )

        File.createTempFile( "cacheFile", ".txt", context.cacheDir)

        context.cacheDir.listFiles().forEach {
            Log.i(TAG, "cacheDir file|dir - name: ${it.absolutePath}")
        }

        File.createTempFile("externalCacheFile", ".txt", context.externalCacheDir)

        context.externalCacheDir?.listFiles()?.forEach {
            Log.i(TAG, "ExternalCacheDir file|dir - name: ${it.absolutePath}")
        }

        /**
         * For external storage it is possible to have multiple physical external storage locations
         * due that some devices use some part of the internal storage and declares it as external
         * in order to get a reference of the external storages
         *
         */


        val externalStorageVolumes = ContextCompat.getExternalFilesDirs(context, null)
        var list:String = "List of physical external volumes\n\r"
        externalStorageVolumes.forEach {
            list.plus("${it.absolutePath}\n\r")
        }
        Log.i(TAG, list)

        //Because the external storage can be removed that implies there should be a way to know if it is available
        // we do that by using the Static Class Environment

        if( Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ) {
            Log.i(TAG, "The external storage is mounted and is writable and readable...")
        }
        if( Environment.getExternalStorageState() ==  Environment.MEDIA_MOUNTED_READ_ONLY ) {
            Log.i(TAG, "The external storage is mounted but it is only readable...")
        }
        // if we want to work with the media directories we know those dirs are shareable between app with the required permissions
        // we should use MediaStore content provider API to work with it  BUT if we want some files are only visible to
        // our own app we can we directly app specific storage and specify one of thos Media Content dirs
        // /storage/emulated/0/Android/data/com.example.storageapp/files/Pictures/MyNewPicture is the dir it implies the dirs are created into the root app dir
        val myNewPicture = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyNewPicture")
        if(!myNewPicture.exists()){
            myNewPicture.createNewFile()
        }
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.listFiles()?.sortedBy {
            file ->
            file.lastModified() }?.forEach { Log.i(TAG,it.absolutePath ) }
    }


    fun addToFile(newDateLaunched:String){
        Log.i(TAG,"internal dir:${context.filesDir}")
        Log.i(TAG,"Adding $newDateLaunched to the file")

        //this.file.bufferedWriter().
        this.file.appendText("$newDateLaunched", Charsets.UTF_8)
        this.file.appendText("\n", Charsets.UTF_8)
        addToMutableList(newDateLaunched)
    }

    private fun addToMutableList(newDateLaunched: String) {
        this.dateArray.add(newDateLaunched)

    }



    companion object{
        const val TAG = "StorageDateHandlerL"
        const val SOURCE_FILE_DATE_LAUNCHED = "SOURCE_FILE_DATE_LAUNCHED"
    }

}