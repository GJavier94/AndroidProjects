package com.example.fragmentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ChildFragment : Fragment() {

    private lateinit var buttonBbackToChildFragment: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(FragmentBottom.TAG,"onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG,"onCreateView")
        val view = inflater.inflate(R.layout.fragment_child, container, false)
        buttonBbackToChildFragment = view.findViewById<FloatingActionButton>(R.id.buttonBbackToChildFragment)
        var alreadyDone = false
        buttonBbackToChildFragment.setOnClickListener {
            if(!alreadyDone){
                alreadyDone=true
                this.parentFragmentManager.popBackStack()
            }
        }

        //Once the fragment is created it is necessary to stablish one of the fragments in this level
        // as the primary navigation so that the button backpressed interact with the back stack of this specific fragment
        this.parentFragmentManager.commit {
            setPrimaryNavigationFragment(this@ChildFragment)
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG,"onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        Log.i(TAG,"onDestroy")
        super.onDestroy()
    }
    companion object {

     const val TAG = "ChildFragment"
    }
}