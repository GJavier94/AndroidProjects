package com.example.storageapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.storage.StorageManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.fragment.app.viewModels
import com.example.storageapp.ViewModels.ViewModelAppSpecificFragment


class AppSpecificStorageFragment : Fragment() {

    private lateinit var buttonSave: Button
    val viewModel: ViewModelAppSpecificFragment by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_app_specific_storage, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listViewDatesLaunched = view.findViewById(R.id.list_view_datesLaunched)
        viewModel.listViewDatesLaunched?.adapter = viewModel.datesLaunchedAdapter

        buttonSave = view.findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            allocateStorage()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun allocateStorage() {

        //In order to save files we should know previously how much space we have so that we know we can store the new file

        //Storage is an attribute to the device the operative system should handle this
        // => there should be a service which handles this => the StorageManager is the name of the service

        val storageManager = this.context?.getSystemService<StorageManager>() // service to level system
        //StorageManager has the following methods

        // getAllocatableBytes() -> gives the free space + the cache space consumed ( we can delete cache data)
        // allocateBytes() -> reserve this space for the new file so that no other apps can use it

        val SPACE_NEEDED:Long = 1024 * 1024 //1 megabyte -> 1024 kilobytes ->1024(1024bytes)
        //everything can be identified with a 128 universal identifier which is called UUID
        //let's define the UUID of the external dir path StorageManager Produces it
        val uuIDExternalDir = context?.getExternalFilesDir(null)?.let { storageManager?.getUuidForPath(it) }
        if( uuIDExternalDir != null ) {
            if(SPACE_NEEDED < storageManager?.getAllocatableBytes(uuIDExternalDir)!!){
                Toast.makeText(context, "Allocating space ", Toast.LENGTH_LONG).show()
                storageManager.allocateBytes(uuIDExternalDir,SPACE_NEEDED)
            }else{
                Toast.makeText(context, "There is no enough space ", Toast.LENGTH_LONG).show()
                val intentStorage = Intent().apply {
                    action = StorageManager.ACTION_MANAGE_STORAGE
                }
            }

        }



    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listViewDatesLaunched = null
    }

    companion object {
        const val TAG = "AppSpecificStorageFragment"
    }
}