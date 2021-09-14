package com.example.storageapp

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.example.storageapp.Adapters.PageAdapter
import com.example.storageapp.ViewModels.ViewModelActivity
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val viewModel: ViewModelActivity by viewModels()

    private lateinit var viewPager: ViewPager2

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = PageAdapter(this, NUM_PAGES)


        if(!viewModel.arePermissionRequested()){
            val arrayPermissionsRequired = viewModel.PERMISSIONS_STORAGE.filter {
                perPair: Pair<String, Int> ->
                perPair.second != PackageManager.PERMISSION_GRANTED
            }.map { it.first }.toTypedArray() // this method helps to convert a list into an arrayList
            
            for(i in 0..viewModel.PERMISSIONS_STORAGE.size-1){
                if(viewModel.PERMISSIONS_STORAGE[i].second != PackageManager.PERMISSION_GRANTED){
                    viewModel.PERMISSIONS_STORAGE[i] = viewModel.PERMISSIONS_STORAGE[i].copy(second = viewModel.PERMISSION_REQUESTED)
                }
            }
             
            
            this.requestPermissions(arrayPermissionsRequired, viewModel.REQUEST_CODE)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var indexPermissions:Int = 0
        if(requestCode == viewModel.REQUEST_CODE){
            for (i in 0..viewModel.PERMISSIONS_STORAGE.size-1){
                if(viewModel.PERMISSIONS_STORAGE[i].second == viewModel.PERMISSION_REQUESTED){
                    viewModel.PERMISSIONS_STORAGE[i] = viewModel.PERMISSIONS_STORAGE[i].copy(second = grantResults[indexPermissions])
                    indexPermissions++
                }
            }
        }


    }

    companion object{
        const val TAG = "MainActivityL"
        const val NUM_PAGES:Int = 2
    }




}