package com.example.fragmentlifecycleapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ChildFragment : Fragment() {


    private lateinit var textViewSubtitle: TextView
    private lateinit var textViewSavedState: TextView
    private lateinit var textViewChildFragment: TextView
    private lateinit var buttonChangeViewState: Button
    private  var variableFragment:Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG,"...onAttach...")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"...onCreate...")

        Log.i(TAG,"variableFragment: $variableFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_child, container, false)
        Log.i(TAG,"...onCreateView...")

        buttonChangeViewState = view.findViewById<Button>(R.id.buttonChangeViewState)
        textViewChildFragment = view.findViewById<TextView>(R.id.textViewChildFragment)
        textViewSavedState = view.findViewById<TextView>(R.id.textViewSavedState)
        textViewSubtitle = view.findViewById<TextView>(R.id.textViewSubtitle)
        /**
         * In a configuration change :
         * The variables of the fragment are not preserved
         * the state of the view is preserved
         * The save state instance is also preserved
         * Nonconfig state is also preserved
         */
        buttonChangeViewState.setOnClickListener {
            var entero = textViewChildFragment.text.toString().toInt()
            textViewChildFragment.text = (++entero).toString()
            variableFragment++
            Log.i(TAG,"variableFragment: $variableFragment buttonChangeViewState")
            textViewSubtitle.text = "The text has been changed"
        }

        var goBackChild = view.findViewById<FloatingActionButton>(R.id.goBackChild)
        goBackChild.setOnClickListener {
            this.parentFragmentManager.popBackStack()
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
        val variableSaveState = savedInstanceState?.getString("variableSaveState")
        Log.i(TAG,"...onViewStateRestored...$variableSaveState")
        textViewSavedState.text = variableSaveState

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
        outState.apply {
            putString("variableSaveState", "This variable has been saved thanks to InstanceState")
        }
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
        const val TAG = "ChildFragmentCLogger"
    }
}