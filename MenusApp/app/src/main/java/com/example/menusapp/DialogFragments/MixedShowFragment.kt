package com.example.menusapp.DialogFragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.menusapp.R

class MixedShowFragment:DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_FRAGMENT, "onCreate")
    }

    /**
     * This is only called when the fragment is shown in a dialog
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.i(TAG_FRAGMENT,"onCreateDialog in case there is a change...")
        return super.onCreateDialog(savedInstanceState)
    }

    //Fragments that can treated as show or as a transaction need to be treated as fragment
    //-> onCreateView needs to be implemented
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG_FRAGMENT, "onCreateView")
        return inflater.inflate(R.layout.mixed_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG_FRAGMENT, "onViewCreated")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG_FRAGMENT, "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG_FRAGMENT, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG_FRAGMENT, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_FRAGMENT, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG_FRAGMENT, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_FRAGMENT, "onDestroy")
    }



    companion object{
        const val TAG_FRAGMENT = "MixedShowFragment"
        const val TAG_FDIALOG = "MixedShowFragmentDialog"
    }
}