package com.example.storageapp

import android.content.ContentUris
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import com.example.storageapp.Models.MediaImage
import com.example.storageapp.ViewModels.ViewModelShareStorageFragment
import java.lang.Exception

class WorkerDataRetriever(var viewModelShareStorageFragment: ViewModelShareStorageFragment) :Runnable{
    private val MESSAGE_BITMAP_SEND: Int = 700
    private val MESSAGE_UPDATE_TEXT: Int = 600
    private val MESSAGE_FINISH_RETRIEVING: Int = 300
    private val MESSAGE_OK: Int = 200
    val TAG = Thread.currentThread().name
    /**
     * Query a media collection
     *
     * To find media that satisfies a particular set of conditions,
     * such as a duration of 5 minutes or longer,
     * use an SQL-like selection statement similar
     */
    lateinit var projection:Array<String>
    lateinit var selection:String
    lateinit var selectionArgs:Array<String>
    lateinit var  sortOrder:String

    val looperUIthread = viewModelShareStorageFragment.applicationContext.mainLooper

    val handler: Handler
    init {
        handler = object: Handler(this.looperUIthread){
            override fun handleMessage(msg: Message) {
                msg.apply {
                    if(this.what == MESSAGE_OK){
                        viewModelShareStorageFragment.textProgress.value = "Receiving in UIThread..."
                        Log.i(TAG, "Receiving in UIThread...")
                        val mediaImage = this.obj as MediaImage
                        viewModelShareStorageFragment.dataSource.value?.apply {
                            add(mediaImage)
                        }
                    }
                    if(this.what == MESSAGE_FINISH_RETRIEVING){
                        Log.i(TAG, "The dataSource has been retrieved")
                        viewModelShareStorageFragment.dataSourceRetrieved.value = true
                    }
                    if (this.what == MESSAGE_UPDATE_TEXT){
                        val updateText = this.obj as String
                        viewModelShareStorageFragment.textProgress.value = updateText
                    }
                }

            }
        }
    }

    private fun prepareQuery(){
        sendUpdateMessage("Preparing query...")

        Log.i(TAG, "Preparing query...")

        projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.DESCRIPTION
        )
        selection = "" // nothing because i want to get the whole table
        selectionArgs = arrayOf() //nothing because i want to get the whole table
        sortOrder = "${MediaStore.Images.ImageColumns._ID} ASC"
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun executeQuery(){
        sendUpdateMessage("Executing query...")
        Log.i(TAG, "Executing query...")

        val cursor = viewModelShareStorageFragment.applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )
        if(cursor != null){
            Log.i(TAG,"Query:The cursor is not null ")
        }
        cursor?.use {
                cursor ->
            val idColumn  = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
            val descriptionColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DESCRIPTION)
            Log.i(TAG, "Query: idColumn:$idColumn, descriptionColumn:$descriptionColumn")
            // we will be moving through the rows of this table and get the values of each field in each row
            while(cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val description = cursor.getString(descriptionColumn)

                //to create the uri of this item its conformed by -> UriTable + id  => using class ContentUris.appendWithId
                val uri  = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)


                // we create the object MediaStore and send it in  to a message

                sendUpdateMessage( "Getting new item with id $id..." )
                Log.i(TAG, "Getting new item with id $id...")

                //trying to get the thumbnail  ==> this code was emmbedded here due that if we iterate and override a collection we receive a concurrentObjectModification
                Log.i(TAG, "Loading thumbnails...")

                val thumbnail: Bitmap? = getThumbNail(id,uri)


                Log.i(TAG, """row: id:$id, description:$description, uri:$uri thumbnail:${if( thumbnail != null) "true" else "false"}""")


                val message = handler.obtainMessage().apply {
                    what = MESSAGE_OK
                    obj = when(thumbnail){
                        null -> {
                            MediaImage(
                                viewModelShareStorageFragment.applicationContext, uri, id, description,)
                        }
                        else ->{
                            MediaImage(
                                viewModelShareStorageFragment.applicationContext, uri, id, description, thumbnail)
                        }
                    }

                }

                sendUpdateMessage( "Sending MediaImage to UIThread...")
                Log.i(TAG, "Sending MediaImage to UIThread...")
                handler.sendMessage(message)
            }

            handler.apply {
                val msg = obtainMessage().apply {
                    what = MESSAGE_FINISH_RETRIEVING
                }
                sendMessage(msg)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getThumbNail(id: Long, contentUri: Uri): Bitmap? {
        return viewModelShareStorageFragment.applicationContext.contentResolver.let {
                contentResolver ->
            var thumbNail: Bitmap? = null
            try{
                thumbNail = if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)) {
                    Log.i(TAG, "SDK_INT At least Build.VERSION_CODES.Q: ${Build.VERSION_CODES.Q}")

                    contentResolver.loadThumbnail(contentUri, Size(320, 240), null)
                } else {
                    Log.i(TAG, "SDK_INT:${Build.VERSION.SDK_INT} lower than  Build.VERSION_CODES.Q: ${Build.VERSION_CODES.Q}")
                    MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MINI_KIND, null)
                }
            }catch (e: Exception){
                Log.i(TAG,"FileNotFoundException: ${e.message}")
            }
            thumbNail
        }

    }


    private fun sendUpdateMessage(s: String) {
        handler.apply {
            val msg = obtainMessage().apply {
                what = MESSAGE_UPDATE_TEXT
                obj = s
            }
            sendMessage(msg)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun run() {
        this.prepareQuery()
        this.executeQuery()
    }

    companion object{
        fun createInstanceAndRun(viewModelShareStorageFragment:ViewModelShareStorageFragment, name:String):Thread{
            val thread = Thread(WorkerDataRetriever(viewModelShareStorageFragment), name)
            thread.start()
            return thread
        }
    }
}
