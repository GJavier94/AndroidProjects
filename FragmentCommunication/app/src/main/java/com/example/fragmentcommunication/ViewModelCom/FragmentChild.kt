package com.example.fragmentcommunication.ViewModelCom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.fragmentcommunication.R
import com.example.fragmentcommunication.ViewModelCom.Models.Person
import com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity.ViewModelFragmentSibling1


class FragmentChild : Fragment() {
    private lateinit var buttonChildFragment: Button
    private val viewModelFragmentSibling1: ViewModelFragmentSibling1 by viewModels({requireParentFragment()})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResult(
            Keys.CHILD_TO_SIBLING_1,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Gloria", "Santos", 21) )
            }
        )
        setFragmentResultListener(
            Keys.SIBLING1_TO_CHILD
        ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(FragmentSibling2.TAG, "person: $person")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonChildFragment = view.findViewById<Button>(R.id.buttonChildFragment)
        buttonChildFragment.setOnClickListener {
            Log.i(TAG, "button changing value ..")
            viewModelFragmentSibling1.counterSibling1toChild.value = viewModelFragmentSibling1.counterSibling1toChild.value?.plus(1)

        }
    }

    companion object{
        const val TAG = "FragmentChildLogger"
    }
}