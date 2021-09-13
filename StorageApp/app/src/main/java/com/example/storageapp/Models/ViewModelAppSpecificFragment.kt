package com.example.storageapp.Models


import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.storageapp.Adapters.DatesLaunchedAdapter
import com.example.storageapp.StorageDateHandler
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class ViewModelAppSpecificFragment(application: Application): AndroidViewModel(application) {

    var listViewDatesLaunched: ListView? = null
    val storageDataHandler:StorageDateHandler =StorageDateHandler(this.getApplication<Application>().applicationContext)
    val dataSource:MutableList<String> = this.storageDataHandler.dateArray
    var  datesLaunchedAdapter: DatesLaunchedAdapter



    init {

        datesLaunchedAdapter = DatesLaunchedAdapter(this.getApplication<Application>().applicationContext, dataSource)

        //set the time we launch the app  ( assuming the view Models is called whenever the app is launched)
        setAppLaunching()
        //after setting the date while creating the ViewModel
        //it is time to create the adapter

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setAppLaunching(){
        val appDateLaunched:String = LocalDateTime.now().toString()
        Log.i(TAG, appDateLaunched)
        this.storageDataHandler.addToFile(appDateLaunched)
        this.datesLaunchedAdapter.notifyDataSetChanged()
    }


    companion object{
        const val TAG = "VmAppSpecificFragmentL"
    }

}