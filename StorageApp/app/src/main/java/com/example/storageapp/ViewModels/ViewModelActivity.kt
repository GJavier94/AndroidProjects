package com.example.storageapp.ViewModels

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.util.jar.Manifest

class ViewModelActivity(application: Application):AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    //ALL the member data associated with PERMISSIONS
    var permissionsRequested:Boolean = false
    var PERMISSIONS_STORAGE:MutableList<Pair<String, Int>> = mutableListOf() // the boolean indicates if the permission was conceded or not
    val REQUEST_CODE = 200
    val PERMISSION_REQUESTED = 5000

    init {
        //inserting pairs of permissions
        PERMISSIONS_STORAGE.apply {
            add(Pair(android.Manifest.permission.READ_EXTERNAL_STORAGE,PackageManager.PERMISSION_DENIED))
            add(Pair(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,PackageManager.PERMISSION_DENIED))
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun arePermissionRequested(): Boolean {
        if(permissionsRequested)return permissionsRequested
        var i:Int
        var requestPermissions = false
        for(i in 0..PERMISSIONS_STORAGE.size-1){
            val isGranted = context.checkSelfPermission( PERMISSIONS_STORAGE[i].first)
            PERMISSIONS_STORAGE[i] = PERMISSIONS_STORAGE[i].copy(second = isGranted )
            if(isGranted == PackageManager.PERMISSION_DENIED) permissionsRequested = false
        }
        return permissionsRequested
    }




}
