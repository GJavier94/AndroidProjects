package com.example.storageapp.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.storageapp.Models.MediaImage
import com.example.storageapp.R
import com.example.storageapp.WorkerDataRetriever



class ViewModelShareStorageFragment(application: Application):AndroidViewModel(application) {
    var applicationContext = getApplication<Application>().applicationContext

    val textProgress:MutableLiveData<String> = MutableLiveData(applicationContext.resources.getString(
        R.string.progress_bar_loading))
    var dataSourceRetrieved:MutableLiveData<Boolean> = MutableLiveData(false)
    val dataSource:MutableLiveData<MutableList<MediaImage>> = MutableLiveData(mutableListOf())

    init {
        //here we should create a worker thread to do the query of the data
        val workerDataRetriever = WorkerDataRetriever.createInstanceAndRun(this, "QueryExecutor")


        //Registering an observer  to know when the Worker execution finishes
        this.dataSourceRetrieved.observeForever {
            isDataSourceRetrieved ->
            if(isDataSourceRetrieved){
                textProgress.value = "The dataSource has been retrieved"
                Log.i(TAG, "The dataSource has been retrieved")
                printDataSource()
            }
        }

    }

    fun printDataSource(){
        var dataSourceString = "\n\r"
        this.dataSource.value?.forEach {
            mediaImage ->
            dataSourceString.plus(mediaImage).plus("""/n/r""")
        }
        Log.i(TAG, "The datasource is: \n$dataSourceString")
    }
    companion object{
        const val TAG = "VMShareStorageFragmentL"
    }
}
