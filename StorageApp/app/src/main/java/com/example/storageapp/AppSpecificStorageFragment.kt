package com.example.storageapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.viewModels
import com.example.storageapp.Models.ViewModelAppSpecificFragment


class AppSpecificStorageFragment : Fragment() {

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



    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listViewDatesLaunched = null
    }

    companion object {
        const val TAG = "AppSpecificStorageFragment"
    }
}