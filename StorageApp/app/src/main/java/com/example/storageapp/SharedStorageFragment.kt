package com.example.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storageapp.Adapters.PicturesAdapter
import com.example.storageapp.ViewModels.ViewModelShareStorageFragment

class SharedStorageFragment: Fragment() {

    private lateinit var progressBarRetrieveData: ProgressBar
    private lateinit var recyclerViewPictures: RecyclerView
    private lateinit var textViewProgressBarText: TextView
    val viewModel: ViewModelShareStorageFragment by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share_storage,container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //let's start observing how the datasource is getting retrieved
        textViewProgressBarText = view.findViewById(R.id.textView_progressbar_text)
        progressBarRetrieveData = view.findViewById<ProgressBar>(R.id.progressBar)
        recyclerViewPictures = view.findViewById(R.id.recyclerView_pictures)

        //UIupdates when the data source is retrieved
        viewModel.dataSourceRetrieved.observe(this.viewLifecycleOwner,{
            isDataSourceRetrieved ->
            if(isDataSourceRetrieved){
                progressBarRetrieveData.visibility = View.INVISIBLE
                textViewProgressBarText.text = getString(R.string.SHOW_FILES)
                textViewProgressBarText.visibility = View.INVISIBLE
                recyclerViewPictures.visibility = View.VISIBLE
            }
        })

        //in our logic the recyclerView is built once the data is retrieve so we will need to wait for the datasource with an Observer
        viewModel.dataSourceRetrieved.observe(this.viewLifecycleOwner,{
            isDataSourceRetrieved ->
            if(isDataSourceRetrieved){

                // let's define the LayoutManager for the RecyclerView
                recyclerViewPictures.layoutManager = LinearLayoutManager(this.context)
                //let's create the adapter and set it into the RecyclerView
                recyclerViewPictures.adapter = PicturesAdapter(viewModel.dataSource) // it need the data source to adapt to the picture view holder
            }
        })


        viewModel.textProgress.observe(this.viewLifecycleOwner, Observer {
            textProgress ->
            textViewProgressBarText.text = textProgress
        })
        //IT is time to create the dataSource which will fill the recyclerView
        //first We will need a view model to hold data from this fragment



    }




}
