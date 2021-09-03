package com.example.fragmentlifecycleapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit

class HostFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG,"...onAttach...")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"...onCreate...")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_host, container, false)
        Log.i(TAG,"...onCreateView...")

        this.childFragmentManager.commit {
            //This creates the instance then attached it to the fragment Manager
            add<ChildFragment>(R.id.fragmentContainerChild, "ChildFragment")
            setPrimaryNavigationFragment( childFragmentManager.findFragmentByTag("ChildFragment"))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG,"...onViewCreated...")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG,"...onViewStateRestored...")
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG,"...onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"...onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"...onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"...onStop...")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"...onSaveInstanceState...")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG,"...onDestroyView...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"...onDestroy...")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG,"...onDetach...")
    }

    companion object{
        const val TAG = "HostFragmentCLogger"
    }

}