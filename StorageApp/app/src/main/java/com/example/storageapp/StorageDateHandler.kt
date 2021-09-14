package com.example.storageapp

import android.content.Context
import android.util.Log
import java.io.File

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
         * mkdir to create a new directory
         * */

        // let's create Nested Directories

        repeat(10){
                position ->
            File(context.filesDir, "privateFiles$position").mkdir()
        }

        context.filesDir.listFiles().forEach {
            Log.i(TAG, it.absolutePath)
        }

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
                Log.i(TAG, "line: $date")

                val regex = """\n""".toRegex()
                if(!date.matches(regex)){
                    this.dateArray.add(date)
                }
            }
        }
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