package com.example.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.storageapp.ViewModels.ViewModelShareStorageFragment

class SharedStorageFragment: Fragment() {

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

        //IT is time to create the dataSource which will fill the recyclerView
        //first We will need a view model to hold data from this fragment



    }




}
