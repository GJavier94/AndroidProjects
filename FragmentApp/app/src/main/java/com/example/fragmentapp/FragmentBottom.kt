package com.example.fragmentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.add
import androidx.fragment.app.commit


class FragmentBottom : Fragment(R.layout.fragment_bottom) {

    private lateinit var  buttonGoChildFragment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG,"onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG,"onCreateView")
        val view = inflater.inflate(R.layout.fragment_bottom, container, false)
        buttonGoChildFragment = view.findViewById<Button>(R.id.buttonGoChildFragment)
        buttonGoChildFragment.setOnClickListener {

            this.childFragmentManager.commit {
                add<ChildFragment>(R.id.fragmentChildContainer, "ChildFragment")
                addToBackStack(null)
            }


        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG,"onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        //Once the fragment is created it is necessary to stablish one of the fragments in this level
        // as the primary navigation so that the button backpressed interact with the back stack of this specific fragment
        this.parentFragmentManager.commit {
            setPrimaryNavigationFragment(this@FragmentBottom)
        }

        val entre:String? = requireArguments().getString("key") ?:"unknown"
        Log.i(TAG, "$entre")
    }

    companion object{
        const val TAG = "FragmentBottom"
    }
}