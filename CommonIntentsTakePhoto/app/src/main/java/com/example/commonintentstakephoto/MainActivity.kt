package com.example.commonintentstakephoto

import android.content.Intent
import android.graphics.BitmapFactory

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var vm:ViewModelMainAct
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm  = ViewModelProvider(this).get(ViewModelMainAct::class.java)

        imageView = findViewById(R.id.imageView)
        findViewById<Button>(R.id.buttonTakePhoto).setOnClickListener {
            // we will create an intent  that calls the photo app
            //take photo
            // the static variables to define the action and the extra are in MediaStore
            // Action:
            //extra -> uri of the photo, its necessary first to create a nameFile ( needs to be unique, we will use
            //timestamp
            // we will use a contentProvider which stores files and returns Uri's
            // the name of the provider is FileProvider and belongs to AndroidX
            //It is necessary to declare it on the manifest file so that we can  set the paths for the files and so on




            Log.i(TAG, "${vm.nameImageFile} ${vm.file.toString()}  ${vm.file?.toURI()}" )
            vm.createImageFile(applicationContext)

            var  uriContentFile: Uri? = vm.getUriContentForFile(applicationContext)
            Log.i(TAG, "The URI for the file is: $uriContentFile ")

            val intent = Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
                putExtra(MediaStore.EXTRA_OUTPUT, uriContentFile)
            }
            //once the intent is made we can make the package manager to look for a manifest file
            // which can resolve our intent request message
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ){
            vm.addToGalleryProvider(vm.nameImageFile!!,applicationContext)

            val bitmap = BitmapFactory.decodeFile(vm.nameImageFile)
            imageView.setImageBitmap(bitmap)
        }else{
            Toast.makeText(this, "Request cancelled or something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
    companion object{
        const val TAG = "MainActivity"
        const val REQUEST_IMAGE_CAPTURE = 200
    }
}